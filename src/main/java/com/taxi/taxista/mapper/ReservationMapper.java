package com.taxi.taxista.mapper;

import com.taxi.taxista.DTO.ReservationDTO;
import com.taxi.taxista.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ReservationMapper {

    @Mapping(target = "driverId", source = "driver.id")
    @Mapping(target = "vehicleId", source = "vehicle.id")
    @Mapping(target = "customerId", source = "customer.id")
    ReservationDTO toDto(Reservation reservation);

    @Mapping(target = "driver.id", source = "driverId")
    @Mapping(target = "vehicle.id", source = "vehicleId")
    @Mapping(target = "customer.id", source = "customerId")
    Reservation toEntity(ReservationDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "driver.id", source = "driverId")
    @Mapping(target = "vehicle.id", source = "vehicleId")
    @Mapping(target = "customer.id", source = "customerId")
    void updateEntityFromDto(ReservationDTO dto, @MappingTarget Reservation entity);
}