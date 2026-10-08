package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Agence;

import java.util.List;

public interface IAgenceService {

    Agence addAgence(Agence agence);

    List<Agence> getAllAgences();

    Agence getAgenceById(Long id);

    Agence updateAgence(Agence agence);

    void deleteAgence(Long id);
}