package com.taxi.taxista.repository;

import com.taxi.taxista.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
//    @Query("SELECT d.id, " +
//            "SUM(CASE WHEN r.statut = 'CONFIRMÉE' OR r.statut = 'TERMINÉE' " +
//            "    THEN TIMESTAMPDIFF(HOUR, r.heureDebutCourse, r.heureFinCourse) " +
//            "    ELSE 0 END) AS totalInCourse, " +
//            "TIMESTAMPDIFF(HOUR, d.disponibiliteDebut, d.disponibiliteFin) AS totalAvailableHours " +
//            "FROM Chauffeur d " +
//            "LEFT JOIN Reservation r ON r.chauffeur.id = d.id " +
//            "WHERE d.id = :chauffeurId " +
//            "GROUP BY d.id")
//    Object[] getOccupationRate(@Param("chauffeurId") Long chauffeurId);
}
