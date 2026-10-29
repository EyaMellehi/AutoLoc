package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Paiement;
import tn.esprit.autoloc.autolocapi.dto.PaiementDTO;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.mapper.PaiementMapper;
import tn.esprit.autoloc.autolocapi.repository.IPaiementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementService implements IPaiementService {

    private final IPaiementRepository paiementRepository;
    private final PaiementMapper paiementMapper;

    @Override
    public PaiementDTO findById(Long id) {

        Paiement paiement =
                paiementRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Paiement",
                                        id
                                )
                        );

        return paiementMapper.toDto(paiement);
    }

    @Override
    public List<PaiementDTO> findAll() {

        return paiementRepository.findAll()
                .stream()
                .map(paiementMapper::toDto)
                .toList();
    }
}