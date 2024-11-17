package com.taxi.taxista.repository;

import com.taxi.taxista.entity.Vehicule;
import com.taxi.taxista.entity.enums.VehiculeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
//    @Query("SELECT v FROM Vehicule v WHERE v.type = :type")
    List<Vehicule> findByType(VehiculeType type);

    @Query("SELECT v.type, AVG(v.mileage) FROM Vehicule v GROUP BY v.type")
    List<Object[]> findAverageMileageByType();

    // Count vehicles by status
    @Query("SELECT v.status, COUNT(v) FROM Vehicule v GROUP BY v.status")
    List<Object[]> countVehiclesByStatus();

    // Utilization rate by vehicle type: calculates percentage of time in 'EN_COURSE' status
    @Query("SELECT v.type, r.heureDebutCourse, r.heureFinCourse, r.status " +
            "FROM Vehicule v JOIN v.reservations r")
    List<Object[]> fetchVehiculeReservationData();




}
