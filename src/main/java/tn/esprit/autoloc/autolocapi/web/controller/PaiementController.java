package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.dto.PaiementDTO;
import tn.esprit.autoloc.autolocapi.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService paiementService;

    @GetMapping
    public List<PaiementDTO> findAll() {
        return paiementService.findAll();
    }

    @GetMapping("/{id}")
    public PaiementDTO findById(
            @PathVariable Long id) {

        return paiementService.findById(id);
    }
}