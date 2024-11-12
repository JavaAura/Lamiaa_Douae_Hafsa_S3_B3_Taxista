package com.taxi.taxista.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO extends UserDTO{

    private String status;
    private LocalTime availabilityStart;
    private LocalTime availabilityEnd;
}
