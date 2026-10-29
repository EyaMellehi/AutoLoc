package tn.esprit.autoloc.autolocapi.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.dto.EmployeDTO;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmployeService employeService;

    @GetMapping
    public List<EmployeDTO> findAll() {
        return employeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeDTO findById(
            @PathVariable Long id) {

        return employeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeDTO create(
            @Valid @RequestBody EmployeDTO dto) {

        return employeService.create(dto);
    }

    @PutMapping("/{id}")
    public EmployeDTO update(
            @PathVariable Long id,
            @Valid @RequestBody EmployeDTO dto) {

        return employeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        employeService.deleteById(id);
    }
}