package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Vehicule;

import java.util.List;

public interface IVehiculeService {

    Vehicule addVehicule(Vehicule vehicule);

    List<Vehicule> getAllVehicules();

    Vehicule getVehiculeById(Long id);

    Vehicule updateVehicule(Vehicule vehicule);

    void deleteVehicule(Long id);
}