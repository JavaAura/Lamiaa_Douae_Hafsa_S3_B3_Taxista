package com.taxi.taxista.entity;

import com.taxi.taxista.DTO.ReservationDTO;
import com.taxi.taxista.entity.enums.ReservationStatus;
import com.taxi.taxista.entity.enums.VehiculeType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateHeure;

    @Column(nullable = false)
    private LocalDateTime heureDebutCourse;

    @Column(nullable = false)
    private LocalDateTime heureFinCourse;

    @Column(nullable = false)
    private String adresseDepart;

    @Column(nullable = false)
    private String adresseArrivee;

    @Column(nullable = false)
    private double prix;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus statut;  // CRÉÉE, CONFIRMÉE, TERMINÉE, ANNULÉE

    @Column(nullable = false)
    private double distanceKm;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = true)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = true)
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private VehiculeType vehiculeType;

    public static ReservationDTO toDto(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setDateHeure(reservation.getDateHeure());
        dto.setHeureDebutCourse(reservation.getHeureDebutCourse());
        dto.setHeureFinCourse(reservation.getHeureFinCourse());
        dto.setAdresseDepart(reservation.getAdresseDepart());
        dto.setAdresseArrivee(reservation.getAdresseArrivee());
        dto.setPrix(reservation.getPrix());
        dto.setStatus(reservation.getStatut());
        dto.setDistanceKm(reservation.getDistanceKm());
        dto.setDriverId(reservation.getDriver().getId());
        dto.setVehiculeId(reservation.getVehicule().getId());
        dto.setCustomerId(reservation.getCustomer().getId());
        dto.setVehiculeType(reservation.getVehiculeType());
        return dto;
    }
}
