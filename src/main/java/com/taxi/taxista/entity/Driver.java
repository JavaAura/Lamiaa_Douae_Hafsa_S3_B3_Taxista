package com.taxi.taxista.entity;

import com.taxi.taxista.entity.enums.DriverStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Data
@DiscriminatorValue("DRIVER")
@AllArgsConstructor
@NoArgsConstructor
public class Driver extends User {

    @NotNull(message = "status cannot be null")
    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    @NotNull(message = "availabilityStart cannot be null")
    private LocalTime availabilityStart;

    @NotNull(message = "availabilityEnd cannot be null")
    private LocalTime availabilityEnd;

}
