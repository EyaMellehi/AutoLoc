package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.domain.Paiement;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.dto.ContratDTO;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.mapper.ContratMapper;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;
import tn.esprit.autoloc.autolocapi.repository.IReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratService implements IContratService {

    private final IContratRepository contratRepository;
    private final IReservationRepository reservationRepository;
    private final ContratMapper contratMapper;

    @Override
    public ContratDTO create(ContratDTO dto) {

        if (dto.getIdContrat() != null) {
            throw new IllegalArgumentException(
                    "L'identifiant doit être null lors de la création"
            );
        }

        Reservation reservation = reservationRepository
                .findById(dto.getReservationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Reservation",
                                dto.getReservationId()
                        )
                );

        Contrat contrat = contratMapper.toEntity(dto);

        contrat.setReservation(reservation);

        if (contrat.getPaiements() != null) {
            for (Paiement paiement : contrat.getPaiements()) {
                paiement.setContrat(contrat);
            }
        }

        return contratMapper.toDto(
                contratRepository.save(contrat)
        );
    }

    @Override
    public ContratDTO findById(Long id) {

        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contrat", id));

        return contratMapper.toDto(contrat);
    }

    @Override
    public List<ContratDTO> findAll() {

        return contratRepository.findAll()
                .stream()
                .map(contratMapper::toDto)
                .toList();
    }

    @Override
    public ContratDTO update(Long id, ContratDTO dto) {

        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contrat", id));

        Reservation reservation = reservationRepository
                .findById(dto.getReservationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Reservation",
                                dto.getReservationId()
                        )
                );

        contrat.setDateSignature(dto.getDateSignature());
        contrat.setMontantTotal(dto.getMontantTotal());
        contrat.setValide(dto.isValide());
        contrat.setReservation(reservation);

        return contratMapper.toDto(
                contratRepository.save(contrat)
        );
    }

    @Override
    public void deleteById(Long id) {

        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }

        contratRepository.deleteById(id);
    }
}