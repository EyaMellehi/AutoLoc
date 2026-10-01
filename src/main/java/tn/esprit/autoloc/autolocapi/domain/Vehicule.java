package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    @Column(nullable = false, length = 30)
    private String immatriculation;

    @Column(nullable = false, length = 50)
    private String marque;

    @Column(nullable = false, length = 50)
    private String modele;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CategorieVehicule categorie;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatutVehicule statut;

    // Plusieurs véhicules appartiennent à une agence
    @ManyToOne
    private Agence agence;

    // Vehicule 1 ---- * Maintenance
    @OneToMany(
            mappedBy = "vehicule"
    )
    private List<Maintenance> maintenances = new ArrayList<>();

    // Vehicule 1 ---- * Reservation
    @OneToMany(
            mappedBy = "vehicule"
    )
    private List<Reservation> reservations = new ArrayList<>();

    // Vehicule * ---- * Equipement
    @ManyToMany
    private List<Equipement> equipements = new ArrayList<>();
}