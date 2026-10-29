package tn.esprit.autoloc.autolocapi.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.dto.MaintenanceDTO;
import tn.esprit.autoloc.autolocapi.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @GetMapping
    public List<MaintenanceDTO> findAll() {
        return maintenanceService.findAll();
    }

    @GetMapping("/{id}")
    public MaintenanceDTO findById(
            @PathVariable Long id) {

        return maintenanceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceDTO create(
            @Valid @RequestBody MaintenanceDTO dto) {

        return maintenanceService.create(dto);
    }

    @PutMapping("/{id}")
    public MaintenanceDTO update(
            @PathVariable Long id,
            @Valid @RequestBody MaintenanceDTO dto) {

        return maintenanceService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        maintenanceService.deleteById(id);
    }
}