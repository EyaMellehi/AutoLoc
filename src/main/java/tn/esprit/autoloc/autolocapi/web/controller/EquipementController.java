package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.autolocapi.service.IEquipementService;

@RestController
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipementService equipementService;
}