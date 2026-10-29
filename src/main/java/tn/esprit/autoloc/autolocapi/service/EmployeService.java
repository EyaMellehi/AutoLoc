package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.dto.EmployeDTO;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.mapper.EmployeMapper;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;
import tn.esprit.autoloc.autolocapi.repository.IEmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeService implements IEmployeService {

    private final IEmployeRepository employeRepository;
    private final IAgenceRepository agenceRepository;
    private final EmployeMapper employeMapper;

    @Override
    public EmployeDTO create(EmployeDTO dto) {

        if (dto.getIdEmploye() != null) {
            throw new IllegalArgumentException(
                    "L'identifiant doit être null lors de la création"
            );
        }

        Agence agence = agenceRepository
                .findById(dto.getAgenceId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Agence",
                                dto.getAgenceId()
                        )
                );

        Employe employe = employeMapper.toEntity(dto);
        employe.setAgence(agence);

        return employeMapper.toDto(
                employeRepository.save(employe)
        );
    }

    @Override
    public EmployeDTO findById(Long id) {

        Employe employe = employeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employe", id));

        return employeMapper.toDto(employe);
    }

    @Override
    public List<EmployeDTO> findAll() {

        return employeRepository.findAll()
                .stream()
                .map(employeMapper::toDto)
                .toList();
    }

    @Override
    public EmployeDTO update(Long id, EmployeDTO dto) {

        Employe employe = employeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employe", id));

        Agence agence = agenceRepository
                .findById(dto.getAgenceId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Agence",
                                dto.getAgenceId()
                        )
                );

        employe.setNom(dto.getNom());
        employe.setPrenom(dto.getPrenom());
        employe.setRole(dto.getRole());
        employe.setAgence(agence);

        return employeMapper.toDto(
                employeRepository.save(employe)
        );
    }

    @Override
    public void deleteById(Long id) {

        if (!employeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employe", id);
        }

        employeRepository.deleteById(id);
    }
}