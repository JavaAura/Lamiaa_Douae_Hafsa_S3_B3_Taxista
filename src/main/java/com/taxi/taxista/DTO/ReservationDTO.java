package com.taxi.taxista.DTO;

import com.taxi.taxista.entity.enums.ReservationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReservationDTO {

    private Long id;

    @NotNull(message = "Date and time of reservation is required")
    private LocalDateTime dateHeure;

    @NotNull(message = "Start time of ride is required")
    private LocalDateTime heureDebutCourse;

    @NotNull(message = "End time of ride is required")
    private LocalDateTime heureFinCourse;

    @NotNull(message = "Pickup address is required")
    private String adresseDepart;

    @NotNull(message = "Dropoff address is required")
    private String adresseArrivee;

    @NotNull(message = "Price is required")
    private double prix;

    @NotNull(message = "Reservation status is required")
    private ReservationStatus statut;

    @NotNull(message = "Driver ID is required")
    private Long driverId;

    @NotNull(message = "Vehicle ID is required")
    private Long vehiculeId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;
}