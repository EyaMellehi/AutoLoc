package tn.esprit.autoloc.autolocapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.dto.VehiculeDTO;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface VehiculeMapper {

    @Mapping(source = "agence.idAgence", target = "agenceId")
    VehiculeDTO toDto(Vehicule vehicule);

    @Mapping(target = "agence", ignore = true)
    Vehicule toEntity(VehiculeDTO dto);
}