package com.taxi.taxista.mapper;

import com.taxi.taxista.DTO.UserDTO;
import com.taxi.taxista.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);
}

