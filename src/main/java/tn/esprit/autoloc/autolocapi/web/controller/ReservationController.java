package tn.esprit.autoloc.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.autolocapi.service.IReservationService;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;
}