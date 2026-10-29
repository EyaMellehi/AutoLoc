package tn.esprit.autoloc.autolocapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.dto.ReservationDTO;

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
    Reservation toEntity(ReservationDTO dto);
}