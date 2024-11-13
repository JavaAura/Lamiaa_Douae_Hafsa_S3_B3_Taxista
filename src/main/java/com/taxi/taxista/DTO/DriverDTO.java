package com.taxi.taxista.DTO;

import com.taxi.taxista.entity.enums.DriverStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO extends UserDTO{

    @NotNull(message = "status cannot be null")
    private DriverStatus status;

    @NotNull(message = "availabilityStart cannot be null")
    private LocalTime availabilityStart;

    @NotNull(message = "availabilityEnd cannot be null")
    private LocalTime availabilityEnd;
}
