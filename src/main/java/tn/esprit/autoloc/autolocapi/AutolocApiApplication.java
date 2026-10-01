package tn.esprit.autoloc.autolocapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.VehiculeRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class AutolocApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutolocApiApplication.class, args);
    }

    @Bean
    CommandLineRunner initVehicules(VehiculeRepository vehiculeRepository) {

        return args -> {

            Vehicule v1 = new Vehicule();

            v1.setImmatriculation("123-TUN-4567");
            v1.setMarque("Peugeot");
            v1.setModele("208");
            v1.setCategorie(CategorieVehicule.CITADINE);
            v1.setTarifJournalier(new BigDecimal("90.00"));
            v1.setStatut(StatutVehicule.DISPONIBLE);

            Vehicule v2 = new Vehicule();

            v2.setImmatriculation("234-TUN-5678");
            v2.setMarque("Renault");
            v2.setModele("Clio");
            v2.setCategorie(CategorieVehicule.CITADINE);
            v2.setTarifJournalier(new BigDecimal("85.00"));
            v2.setStatut(StatutVehicule.DISPONIBLE);

            Vehicule v3 = new Vehicule();

            v3.setImmatriculation("345-TUN-6789");
            v3.setMarque("Volkswagen");
            v3.setModele("T-Roc");
            v3.setCategorie(CategorieVehicule.SUV);
            v3.setTarifJournalier(new BigDecimal("140.00"));
            v3.setStatut(StatutVehicule.MAINTENANCE);

            vehiculeRepository.save(v1);
            vehiculeRepository.save(v2);
            vehiculeRepository.save(v3);

            System.out.println("3 véhicules ajoutés avec succès !");
        };
    }
}