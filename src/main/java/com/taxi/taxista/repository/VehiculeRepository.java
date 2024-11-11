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

}
