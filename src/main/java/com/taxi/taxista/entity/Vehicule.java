package com.taxi.taxista.entity;

import javax.persistence.*;

import com.taxi.taxista.entity.enums.VehiculeStatus;
import com.taxi.taxista.entity.enums.VehiculeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    private String registrationNumber;

    private int mileage;

    @Enumerated(EnumType.STRING)
    private VehiculeStatus status;

    @Enumerated(EnumType.STRING)
    private VehiculeType type;

//    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Reservation> reservations;

}
