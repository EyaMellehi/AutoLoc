package tn.esprit.autoloc.autolocapi.dto;

import lombok.*;
import tn.esprit.autoloc.autolocapi.domain.StatutReservation;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponseDTO {

    private Long idReservation;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    private StatutReservation statut;

    private Long clientId;
    private String clientNom;
    private String clientPrenom;

    private Long vehiculeId;

    private Long contratId;
}