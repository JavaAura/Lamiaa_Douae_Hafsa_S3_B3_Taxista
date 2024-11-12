package com.taxi.taxista.mapper;

import com.taxi.taxista.DTO.CustomerDTO;
import com.taxi.taxista.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CustomerMapper {
    Customer toEntity(CustomerDTO customerDTO);
    CustomerDTO toDTO(Customer customer);
    void updateEntityFromDTO(CustomerDTO dto, @MappingTarget Customer entity);
}
