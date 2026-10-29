package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.dto.VehiculeDTO;

import java.util.List;

public interface IVehiculeService {

    VehiculeDTO create(VehiculeDTO dto);

    VehiculeDTO findById(Long id);

    List<VehiculeDTO> findAll();

    VehiculeDTO update(Long id, VehiculeDTO dto);

    void deleteById(Long id);
}