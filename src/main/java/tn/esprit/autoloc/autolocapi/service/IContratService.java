package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Contrat;

import java.util.List;

public interface IContratService {

    Contrat addContrat(Contrat contrat);

    List<Contrat> getAllContrats();

    Contrat getContratById(Long id);

    Contrat updateContrat(Contrat contrat);

    void deleteContrat(Long id);
}