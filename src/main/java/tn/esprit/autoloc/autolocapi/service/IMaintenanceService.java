package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {

    Maintenance addMaintenance(Maintenance maintenance);

    List<Maintenance> getAllMaintenances();

    Maintenance getMaintenanceById(Long id);

    Maintenance updateMaintenance(Maintenance maintenance);

    void deleteMaintenance(Long id);
}