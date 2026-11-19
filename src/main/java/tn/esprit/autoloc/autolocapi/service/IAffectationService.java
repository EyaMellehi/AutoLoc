package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.dto.*;

import java.time.LocalDate;

public interface IAffectationService {

    ReservationResponseDTO affecterReservationAClient(
            Long idReservation,
            String nom,
            String prenom
    );

    VehiculeDTO affecterVehiculeAEquipement(
            Long idVehicule,
            Long idEquipement
    );

    VehiculeDTO affecterMaintenanceAvehicule(
            String description,
            String immatriculation
    );

    ContratDTO affecterContratAReservation(
            LocalDate dateSignature,
            LocalDate dateDebut,
            LocalDate dateFin
    );

    ReservationResponseDTO creerReservationEtContratAssocie(
            ReservationWithContratRequestDTO requestDTO
    );
}