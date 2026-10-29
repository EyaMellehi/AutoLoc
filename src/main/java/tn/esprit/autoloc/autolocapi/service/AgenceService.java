package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.dto.AgenceDTO;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.mapper.AgenceMapper;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceService implements IAgenceService {

    private final IAgenceRepository agenceRepository;
    private final AgenceMapper agenceMapper;

    @Override
    public AgenceDTO create(AgenceDTO agenceDTO) {

        Agence agence = agenceMapper.toEntity(agenceDTO);

        agence.setIdAgence(null);

        Agence savedAgence = agenceRepository.save(agence);

        return agenceMapper.toDto(savedAgence);
    }

    @Override
    public AgenceDTO findById(Long id) {

        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agence", id));

        return agenceMapper.toDto(agence);
    }

    @Override
    public List<AgenceDTO> findAll() {

        return agenceRepository.findAll()
                .stream()
                .map(agenceMapper::toDto)
                .toList();
    }

    @Override
    public AgenceDTO update(Long id, AgenceDTO agenceDTO) {

        Agence existante = agenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agence", id));

        existante.setNom(agenceDTO.getNom());
        existante.setVille(agenceDTO.getVille());
        existante.setAdresse(agenceDTO.getAdresse());
        existante.setTelephone(agenceDTO.getTelephone());

        Agence updatedAgence = agenceRepository.save(existante);

        return agenceMapper.toDto(updatedAgence);
    }

    @Override
    public void deleteById(Long id) {

        if (!agenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence", id);
        }

        agenceRepository.deleteById(id);
    }
}