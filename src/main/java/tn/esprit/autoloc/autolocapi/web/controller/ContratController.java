package tn.esprit.autoloc.autolocapi.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.dto.ContratDTO;
import tn.esprit.autoloc.autolocapi.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;

    @GetMapping
    public List<ContratDTO> findAll() {
        return contratService.findAll();
    }

    @GetMapping("/{id}")
    public ContratDTO findById(
            @PathVariable Long id) {

        return contratService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContratDTO create(
            @Valid @RequestBody ContratDTO dto) {

        return contratService.create(dto);
    }

    @PutMapping("/{id}")
    public ContratDTO update(
            @PathVariable Long id,
            @Valid @RequestBody ContratDTO dto) {

        return contratService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        contratService.deleteById(id);
    }
}