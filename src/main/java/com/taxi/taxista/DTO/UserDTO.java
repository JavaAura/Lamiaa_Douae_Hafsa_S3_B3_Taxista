package com.taxi.taxista.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull(message = "Firstname cannot be null")
    private String firstname;

    @NotNull(message = "lastname cannot be null")
    private String lastname;
}
