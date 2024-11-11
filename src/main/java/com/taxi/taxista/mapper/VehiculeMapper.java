package com.taxi.taxista.mapper;

import com.taxi.taxista.DTO.VehiculeDTO;
import com.taxi.taxista.entity.Vehicule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface VehiculeMapper {
    Vehicule toEntity(VehiculeDTO vehiculeDTO);
    VehiculeDTO toDTO(Vehicule vehicule);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(VehiculeDTO dto, @MappingTarget Vehicule entity);
}
