package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.dto.ReservationDTO;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.mapper.ReservationMapper;
import tn.esprit.autoloc.autolocapi.repository.IClientRepository;
import tn.esprit.autoloc.autolocapi.repository.IReservationRepository;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService
        implements IReservationService {

    private final IReservationRepository reservationRepository;
    private final IClientRepository clientRepository;
    private final IVehiculeRepository vehiculeRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public ReservationDTO create(ReservationDTO dto) {

        validateDates(dto);

        Client client = clientRepository
                .findById(dto.getClientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Client",
                                dto.getClientId()
                        )
                );

        Vehicule vehicule = vehiculeRepository
                .findById(dto.getVehiculeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicule",
                                dto.getVehiculeId()
                        )
                );

        Reservation reservation =
                reservationMapper.toEntity(dto);

        reservation.setClient(client);
        reservation.setVehicule(vehicule);

        return reservationMapper.toDto(
                reservationRepository.save(reservation)
        );
    }

    @Override
    public ReservationDTO findById(Long id) {

        Reservation reservation =
                reservationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Reservation",
                                        id
                                )
                        );

        return reservationMapper.toDto(reservation);
    }

    @Override
    public List<ReservationDTO> findAll() {

        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::toDto)
                .toList();
    }

    @Override
    public ReservationDTO update(
            Long id,
            ReservationDTO dto
    ) {

        validateDates(dto);

        Reservation reservation =
                reservationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Reservation",
                                        id
                                )
                        );

        Client client = clientRepository
                .findById(dto.getClientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Client",
                                dto.getClientId()
                        )
                );

        Vehicule vehicule = vehiculeRepository
                .findById(dto.getVehiculeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicule",
                                dto.getVehiculeId()
                        )
                );

        reservation.setDateDebut(dto.getDateDebut());
        reservation.setDateFin(dto.getDateFin());
        reservation.setStatut(dto.getStatut());
        reservation.setClient(client);
        reservation.setVehicule(vehicule);

        return reservationMapper.toDto(
                reservationRepository.save(reservation)
        );
    }

    @Override
    public void deleteById(Long id) {

        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Reservation",
                    id
            );
        }

        reservationRepository.deleteById(id);
    }

    private void validateDates(ReservationDTO dto) {

        if (dto.getDateDebut() != null
                && dto.getDateFin() != null
                && dto.getDateFin()
                .isBefore(dto.getDateDebut())) {

            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début"
            );
        }
    }
}