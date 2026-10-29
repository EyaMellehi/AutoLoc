package tn.esprit.autoloc.autolocapi.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.dto.ReservationDTO;
import tn.esprit.autoloc.autolocapi.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @GetMapping
    public List<ReservationDTO> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public ReservationDTO findById(
            @PathVariable Long id) {

        return reservationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDTO create(
            @Valid @RequestBody ReservationDTO dto) {

        return reservationService.create(dto);
    }

    @PutMapping("/{id}")
    public ReservationDTO update(
            @PathVariable Long id,
            @Valid @RequestBody ReservationDTO dto) {

        return reservationService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
    }
}