package tn.esprit.autoloc.autolocapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import tn.esprit.autoloc.autolocapi.domain.StatutReservation;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationWithContratRequestDTO {

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    @NotNull
    private StatutReservation statut;

    private Long clientId;

    private Long vehiculeId;

    @NotNull
    private LocalDate dateSignature;

    @NotNull
    @PositiveOrZero
    private BigDecimal montantTotal;

    private boolean valide;
}