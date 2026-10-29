package tn.esprit.autoloc.autolocapi.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.dto.EquipementDTO;
import tn.esprit.autoloc.autolocapi.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipementService equipementService;

    @GetMapping
    public List<EquipementDTO> findAll() {
        return equipementService.findAll();
    }

    @GetMapping("/{id}")
    public EquipementDTO findById(
            @PathVariable Long id) {

        return equipementService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipementDTO create(
            @Valid @RequestBody EquipementDTO dto) {

        return equipementService.create(dto);
    }

    @PutMapping("/{id}")
    public EquipementDTO update(
            @PathVariable Long id,
            @Valid @RequestBody EquipementDTO dto) {

        return equipementService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        equipementService.deleteById(id);
    }
}