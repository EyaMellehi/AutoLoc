package tn.esprit.autoloc.autolocapi.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 50)
    private String nom;
    @Column(nullable = false, length = 50)
    private String ville;
    @Column(nullable = false, length = 50)
    private String adresse;
    @Column(nullable = false, length = 20)
    private String telephone;
    // Agence 1 ---- * Employe
    @OneToMany(
            mappedBy = "agence"
    )
    private List<Employe> employes = new ArrayList<>();

    // Agence 1 ---- * Vehicule
    @OneToMany(
            mappedBy = "agence"
    )
    private List<Vehicule> vehicules = new ArrayList<>();


}
