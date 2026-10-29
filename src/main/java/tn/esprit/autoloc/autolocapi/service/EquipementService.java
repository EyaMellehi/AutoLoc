package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.dto.EquipementDTO;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.mapper.EquipementMapper;
import tn.esprit.autoloc.autolocapi.repository.IEquipementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementService
        implements IEquipementService {

    private final IEquipementRepository equipementRepository;
    private final EquipementMapper equipementMapper;

    @Override
    public EquipementDTO create(EquipementDTO dto) {

        if (dto.getIdEquipement() != null) {
            throw new IllegalArgumentException(
                    "L'identifiant doit être null lors de la création"
            );
        }

        Equipement equipement =
                equipementMapper.toEntity(dto);

        return equipementMapper.toDto(
                equipementRepository.save(equipement)
        );
    }

    @Override
    public EquipementDTO findById(Long id) {

        Equipement equipement =
                equipementRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Equipement",
                                        id
                                )
                        );

        return equipementMapper.toDto(equipement);
    }

    @Override
    public List<EquipementDTO> findAll() {

        return equipementRepository.findAll()
                .stream()
                .map(equipementMapper::toDto)
                .toList();
    }

    @Override
    public EquipementDTO update(
            Long id,
            EquipementDTO dto
    ) {

        Equipement equipement =
                equipementRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Equipement",
                                        id
                                )
                        );

        equipement.setLibelle(dto.getLibelle());

        return equipementMapper.toDto(
                equipementRepository.save(equipement)
        );
    }

    @Override
    public void deleteById(Long id) {

        if (!equipementRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Equipement",
                    id
            );
        }

        equipementRepository.deleteById(id);
    }
}