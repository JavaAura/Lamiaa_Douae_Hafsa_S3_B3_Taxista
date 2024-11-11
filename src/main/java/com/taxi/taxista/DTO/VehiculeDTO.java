package com.taxi.taxista.DTO;

import com.taxi.taxista.entity.enums.VehiculeStatus;
import com.taxi.taxista.entity.enums.VehiculeType;
import lombok.Data;

import javax.validation.constraints.*;

import java.io.Serializable;

@Data
public class VehiculeDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Model is required")
    @Size(max = 50, message = "Model cannot exceed 50 characters")
    private String model;

    @NotBlank(message = "Registration number is required")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "Invalid registration number format")
    private String registrationNumber;

    @Min(value = 0, message = "Mileage must be zero or positive")
    private int mileage;

    @NotNull(message = "Status is required")
    private VehiculeStatus status;

    @NotNull(message = "Type is required")
    private VehiculeType type;
}
