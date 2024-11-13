package com.taxi.taxista.mapper;

import com.taxi.taxista.DTO.CustomerDTO;
import com.taxi.taxista.DTO.DriverDTO;
import com.taxi.taxista.entity.Customer;
import com.taxi.taxista.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DriverMapper {

    Driver toEntity(DriverDTO driverDTO);
    DriverDTO toDTO(Driver driver);
    void updateEntityFromDTO(DriverDTO dto, @MappingTarget Driver entity);
}
