package tn.esprit.autoloc.autolocapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.dto.ReservationDTO;
import tn.esprit.autoloc.autolocapi.dto.ReservationResponseDTO;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ReservationMapper {

    @Mapping(source = "client.idClient", target = "clientId")
    @Mapping(source = "vehicule.idVehicule", target = "vehiculeId")
    ReservationDTO toDto(Reservation reservation);


    @Mapping(target = "client", ignore = true)
    @Mapping(target = "vehicule", ignore = true)
    @Mapping(target = "contrat", ignore = true)
    Reservation toEntity(ReservationDTO dto);


    @Mapping(source = "client.idClient", target = "clientId")
    @Mapping(source = "client.nom", target = "clientNom")
    @Mapping(source = "client.prenom", target = "clientPrenom")
    @Mapping(source = "vehicule.idVehicule", target = "vehiculeId")
    @Mapping(source = "contrat.idContrat", target = "contratId")
    ReservationResponseDTO toResponseDTO(Reservation reservation);
}