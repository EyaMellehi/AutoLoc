package tn.esprit.autoloc.autolocapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.dto.EmployeDTO;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EmployeMapper {

    @Mapping(source = "agence.idAgence", target = "agenceId")
    EmployeDTO toDto(Employe employe);

    @Mapping(target = "agence", ignore = true)
    Employe toEntity(EmployeDTO dto);
}