package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.dto.MaintenanceDTO;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.mapper.MaintenanceMapper;
import tn.esprit.autoloc.autolocapi.repository.IMaintenanceRepository;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService
        implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;
    private final IVehiculeRepository vehiculeRepository;
    private final MaintenanceMapper maintenanceMapper;

    @Override
    public MaintenanceDTO create(MaintenanceDTO dto) {

        validateDates(dto);

        Vehicule vehicule = vehiculeRepository
                .findById(dto.getVehiculeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicule",
                                dto.getVehiculeId()
                        )
                );

        Maintenance maintenance =
                maintenanceMapper.toEntity(dto);

        maintenance.setVehicule(vehicule);

        return maintenanceMapper.toDto(
                maintenanceRepository.save(maintenance)
        );
    }

    @Override
    public MaintenanceDTO findById(Long id) {

        Maintenance maintenance =
                maintenanceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Maintenance",
                                        id
                                )
                        );

        return maintenanceMapper.toDto(maintenance);
    }

    @Override
    public List<MaintenanceDTO> findAll() {

        return maintenanceRepository.findAll()
                .stream()
                .map(maintenanceMapper::toDto)
                .toList();
    }

    @Override
    public MaintenanceDTO update(
            Long id,
            MaintenanceDTO dto
    ) {

        validateDates(dto);

        Maintenance maintenance =
                maintenanceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Maintenance",
                                        id
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

        maintenance.setDateDebut(dto.getDateDebut());
        maintenance.setDateFin(dto.getDateFin());
        maintenance.setDescription(dto.getDescription());
        maintenance.setVehicule(vehicule);

        return maintenanceMapper.toDto(
                maintenanceRepository.save(maintenance)
        );
    }

    @Override
    public void deleteById(Long id) {

        if (!maintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Maintenance",
                    id
            );
        }

        maintenanceRepository.deleteById(id);
    }

    private void validateDates(MaintenanceDTO dto) {

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