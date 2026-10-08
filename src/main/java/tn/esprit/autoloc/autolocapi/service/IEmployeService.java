package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Employe;

import java.util.List;

public interface IEmployeService {

    Employe addEmploye(Employe employe);

    List<Employe> getAllEmployes();

    Employe getEmployeById(Long id);

    Employe updateEmploye(Employe employe);

    void deleteEmploye(Long id);
}