package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;

import java.util.Optional;

public interface IMaintenanceRepository extends JpaRepository<Maintenance, Long> {
    Optional<Maintenance> findByDescription(String description);
}