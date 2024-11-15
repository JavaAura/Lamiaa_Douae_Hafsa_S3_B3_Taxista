package com.taxi.taxista.entity;

import com.taxi.taxista.entity.enums.ReservationStatus;
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
    private ReservationStatus statut;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}