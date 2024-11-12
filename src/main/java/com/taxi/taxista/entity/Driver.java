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

    @NotNull
    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    @NotNull
    private LocalTime availabilityStart;

    @NotNull
    private LocalTime availabilityEnd;

}
