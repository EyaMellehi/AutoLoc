package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.autolocapi.service.IPaiementService;

@RestController
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService paiementService;
}