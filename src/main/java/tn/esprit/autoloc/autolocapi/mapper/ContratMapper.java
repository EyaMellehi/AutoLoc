package tn.esprit.autoloc.autolocapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.dto.ContratDTO;

@Mapper(
        componentModel = "spring",
        uses = PaiementMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ContratMapper {

    @Mapping(source = "reservation.idReservation", target = "reservationId")
    ContratDTO toDto(Contrat contrat);

    @Mapping(target = "reservation", ignore = true)
    Contrat toEntity(ContratDTO dto);
}