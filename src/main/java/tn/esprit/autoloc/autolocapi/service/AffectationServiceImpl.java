package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.autoloc.autolocapi.domain.*;
import tn.esprit.autoloc.autolocapi.dto.*;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.mapper.ContratMapper;
import tn.esprit.autoloc.autolocapi.mapper.ReservationMapper;
import tn.esprit.autoloc.autolocapi.mapper.VehiculeMapper;
import tn.esprit.autoloc.autolocapi.repository.*;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class AffectationServiceImpl implements IAffectationService {

    private final IReservationRepository reservationRepository;
    private final IClientRepository clientRepository;

    private final IVehiculeRepository vehiculeRepository;
    private final IEquipementRepository equipementRepository;

    private final IMaintenanceRepository maintenanceRepository;

    private final IContratRepository contratRepository;

    private final ReservationMapper reservationMapper;
    private final VehiculeMapper vehiculeMapper;
    private final ContratMapper contratMapper;


    // =========================================================
    // 1. AFFECTER RESERVATION A CLIENT
    // =========================================================

    @Override
    public ReservationResponseDTO affecterReservationAClient(
            Long idReservation,
            String nom,
            String prenom) {

        Reservation reservation = reservationRepository
                .findById(idReservation)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Reservation",
                                idReservation
                        )
                );

        Client client = clientRepository
                .findByNomAndPrenom(nom, prenom)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Client introuvable : "
                                        + nom + " " + prenom
                        )
                );

        reservation.setClient(client);

        Reservation savedReservation =
                reservationRepository.save(reservation);

        return reservationMapper.toResponseDTO(savedReservation);
    }


    // =========================================================
    // 2. AFFECTER VEHICULE A EQUIPEMENT
    // =========================================================

    @Override
    public VehiculeDTO affecterVehiculeAEquipement(
            Long idVehicule,
            Long idEquipement) {

        Vehicule vehicule = vehiculeRepository
                .findById(idVehicule)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicule",
                                idVehicule
                        )
                );

        Equipement equipement = equipementRepository
                .findById(idEquipement)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Equipement",
                                idEquipement
                        )
                );

        vehicule.getEquipements().add(equipement);

        /*
         * Si ton association ManyToMany est bidirectionnelle
         * et Equipement possède également getVehicules(),
         * ajoute aussi :
         *
         * equipement.getVehicules().add(vehicule);
         */

        Vehicule savedVehicule =
                vehiculeRepository.save(vehicule);

        return vehiculeMapper.toDto(savedVehicule);
    }


    // =========================================================
    // 3. AFFECTER MAINTENANCE A VEHICULE
    // =========================================================

    @Override
    public VehiculeDTO affecterMaintenanceAvehicule(
            String description,
            String immatriculation) {

        Maintenance maintenance = maintenanceRepository
                .findByDescription(description)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Maintenance introuvable : " + description
                        )
                );

        Vehicule vehicule = vehiculeRepository
                .findByImmatriculation(immatriculation)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Vehicule introuvable avec immatriculation : "
                                        + immatriculation
                        )
                );

        maintenance.setVehicule(vehicule);

        maintenanceRepository.save(maintenance);

        return vehiculeMapper.toDto(vehicule);
    }




    // =========================================================
    // 4. AFFECTER CONTRAT A RESERVATION
    // =========================================================

    @Override
    public ContratDTO affecterContratAReservation(
            LocalDate dateSignature,
            LocalDate dateDebut,
            LocalDate dateFin) {

        Contrat contrat = contratRepository
                .findByDateSignature(dateSignature)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Contrat introuvable pour la date : "
                                        + dateSignature
                        )
                );

        Reservation reservation = reservationRepository
                .findByDateDebutAndDateFin(
                        dateDebut,
                        dateFin
                )
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Reservation introuvable entre "
                                        + dateDebut
                                        + " et "
                                        + dateFin
                        )
                );

        /*
         * Contrat est supposé propriétaire de la relation.
         */
        contrat.setReservation(reservation);

        /*
         * Synchronisation du côté inverse.
         */
        reservation.setContrat(contrat);

        Contrat savedContrat =
                contratRepository.save(contrat);

        return contratMapper.toDto(savedContrat);
    }


    // =========================================================
    // 5. CREER RESERVATION ET CONTRAT ASSOCIE
    // =========================================================

    @Override
    public ReservationResponseDTO creerReservationEtContratAssocie(
            ReservationWithContratRequestDTO requestDTO) {

        /*
         * Vérification des dates
         */
        if (requestDTO.getDateFin()
                .isBefore(requestDTO.getDateDebut())) {

            throw new IllegalArgumentException(
                    "La date de fin doit être postérieure " +
                            "ou égale à la date de début."
            );
        }

        // -----------------------------------------------------
        // Création Reservation
        // -----------------------------------------------------

        Reservation reservation = new Reservation();

        reservation.setDateDebut(
                requestDTO.getDateDebut()
        );

        reservation.setDateFin(
                requestDTO.getDateFin()
        );

        reservation.setStatut(
                requestDTO.getStatut()
        );


        // -----------------------------------------------------
        // Affectation Client si fourni
        // -----------------------------------------------------

        if (requestDTO.getClientId() != null) {

            Client client = clientRepository
                    .findById(requestDTO.getClientId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Client",
                                    requestDTO.getClientId()
                            )
                    );

            reservation.setClient(client);
        }


        // -----------------------------------------------------
        // Affectation Vehicule si fourni
        // -----------------------------------------------------

        if (requestDTO.getVehiculeId() != null) {

            Vehicule vehicule = vehiculeRepository
                    .findById(requestDTO.getVehiculeId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Vehicule",
                                    requestDTO.getVehiculeId()
                            )
                    );

            reservation.setVehicule(vehicule);
        }


        /*
         * On sauvegarde d'abord la reservation.
         */
        Reservation savedReservation =
                reservationRepository.save(reservation);


        // -----------------------------------------------------
        // Création Contrat
        // -----------------------------------------------------

        Contrat contrat = new Contrat();

        contrat.setDateSignature(
                requestDTO.getDateSignature()
        );

        contrat.setMontantTotal(
                requestDTO.getMontantTotal()
        );

        contrat.setValide(
                requestDTO.isValide()
        );


        // -----------------------------------------------------
        // Association Reservation <-> Contrat
        // -----------------------------------------------------

        contrat.setReservation(savedReservation);

        savedReservation.setContrat(contrat);


        /*
         * Contrat propriétaire de la relation
         */
        Contrat savedContrat =
                contratRepository.save(contrat);


        /*
         * On rattache le contrat à l'objet reservation
         * retourné pour que le mapper voie son id.
         */
        savedReservation.setContrat(savedContrat);


        return reservationMapper
                .toResponseDTO(savedReservation);
    }
}