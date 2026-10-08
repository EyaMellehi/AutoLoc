package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Equipement;

import java.util.List;

public interface IEquipementService {

    Equipement addEquipement(Equipement equipement);

    List<Equipement> getAllEquipements();

    Equipement getEquipementById(Long id);

    Equipement updateEquipement(Equipement equipement);

    void deleteEquipement(Long id);
}