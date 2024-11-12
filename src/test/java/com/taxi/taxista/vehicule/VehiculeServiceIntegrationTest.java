package com.taxi.taxista.vehicule;

import com.taxi.taxista.DTO.VehiculeDTO;
import com.taxi.taxista.entity.Vehicule;
import com.taxi.taxista.entity.enums.VehiculeStatus;
import com.taxi.taxista.entity.enums.VehiculeType;
import com.taxi.taxista.repository.VehiculeRepository;
import com.taxi.taxista.service.VehiculeService;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class VehiculeServiceIntegrationTest {

    @Autowired
    private VehiculeService vehiculeService;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    private VehiculeDTO vehiculeDTO;

    @BeforeEach
    void setUp() {
        vehiculeDTO = new VehiculeDTO();
        vehiculeDTO.setModel("Test Model");
        vehiculeDTO.setRegistrationNumber("XYZ-123");
        vehiculeDTO.setMileage(15000);
        vehiculeDTO.setStatus(VehiculeStatus.AVAILABLE);
        vehiculeDTO.setType(VehiculeType.BERLINE);
    }

    @Test
    void testSaveVehicule() {
        VehiculeDTO savedVehiculeDTO = vehiculeService.saveVehicule(vehiculeDTO);

        assertNotNull(savedVehiculeDTO);
        assertEquals(vehiculeDTO.getModel(), savedVehiculeDTO.getModel());
        assertNotNull(savedVehiculeDTO.getId());

        Vehicule vehicule = vehiculeRepository.findById(savedVehiculeDTO.getId()).orElseThrow(null);
        assertEquals(savedVehiculeDTO.getModel(), vehicule.getModel());
        assertEquals(savedVehiculeDTO.getRegistrationNumber(), vehicule.getRegistrationNumber());
    }

    @Test
    void testFindAllVehicules() {
        vehiculeService.saveVehicule(vehiculeDTO);
        vehiculeDTO.setModel("Test Model 2");
        vehiculeDTO.setRegistrationNumber("ABC-123");
        vehiculeService.saveVehicule(vehiculeDTO);

        var vehicules = vehiculeService.findAll(0, 10);
        assertEquals(2, vehicules.getTotalElements());
    }

    @Test
    void testGetVehiculeById() {

        VehiculeDTO savedVehiculeDTO = vehiculeService.saveVehicule(vehiculeDTO);

        VehiculeDTO retrievedVehiculeDTO = vehiculeService.getVehiculeById(savedVehiculeDTO.getId())
                .orElseThrow(() -> new AssertionError("Véhicule non trouvé"));

        assertEquals(savedVehiculeDTO.getModel(), retrievedVehiculeDTO.getModel());
        assertEquals(savedVehiculeDTO.getId(), retrievedVehiculeDTO.getId());
    }

    @Test
    void testUpdateVehicule() {
        VehiculeDTO savedVehiculeDTO = vehiculeService.saveVehicule(vehiculeDTO);

        savedVehiculeDTO.setModel("Updated Model");
        VehiculeDTO updatedVehiculeDTO = vehiculeService.updateVehicule(savedVehiculeDTO.getId(), savedVehiculeDTO);

        assertEquals("Updated Model", updatedVehiculeDTO.getModel());

        Vehicule vehicule = vehiculeRepository.findById(savedVehiculeDTO.getId()).orElseThrow(null);
        assertEquals("Updated Model", vehicule.getModel());
    }

    @Test
    void testDeleteVehicule() {

        VehiculeDTO savedVehiculeDTO = vehiculeService.saveVehicule(vehiculeDTO);

        vehiculeService.deleteVehicule(savedVehiculeDTO.getId());

        assertFalse(vehiculeRepository.existsById(savedVehiculeDTO.getId()));
    }
}
