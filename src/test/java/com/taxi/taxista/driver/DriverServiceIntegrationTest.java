package com.taxi.taxista.driver;

import com.taxi.taxista.DTO.DriverDTO;
import com.taxi.taxista.entity.Driver;
import com.taxi.taxista.entity.enums.DriverStatus;
import com.taxi.taxista.repository.DriverRepository;
import com.taxi.taxista.service.DriverService;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class DriverServiceIntegrationTest {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverRepository driverRepository;

    private DriverDTO driverDTO;

    @BeforeEach
    void setUp() {
        driverDTO = new DriverDTO();
        driverDTO.setFirstname("John");
        driverDTO.setLastname("Doe");
        driverDTO.setStatus(DriverStatus.AVAILABLE);

        driverDTO.setAvailabilityStart(LocalTime.of(9, 0));
        driverDTO.setAvailabilityEnd(LocalTime.of(17, 0));
    }

    @Test
    void testSaveDriver() {
        DriverDTO savedDriverDTO = driverService.saveDriver(driverDTO);

        assertNotNull(savedDriverDTO);
        assertEquals(driverDTO.getFirstname(), savedDriverDTO.getFirstname());
        assertNotNull(savedDriverDTO.getId());

        Driver driver = driverRepository.findById(savedDriverDTO.getId()).orElseThrow(null);
        assertEquals(savedDriverDTO.getFirstname(), driver.getFirstname());
        assertEquals(savedDriverDTO.getLastname(), driver.getLastname());
    }

    @Test
    void testFindAllDrivers() {
        // Save two drivers
        driverService.saveDriver(driverDTO);
        driverDTO.setFirstname("Jane");
        driverDTO.setLastname("Smith");
        driverService.saveDriver(driverDTO);

        // Fetch all drivers (without pagination)
        List<DriverDTO> drivers = driverService.getAllDrivers(); // Returns a list now

        // Assert that 2 drivers are present
        assertEquals(2, drivers.size());
    }

    @Test
    void testGetDriverById() {
        DriverDTO savedDriverDTO = driverService.saveDriver(driverDTO);

        DriverDTO retrievedDriverDTO = driverService.getDriverById(savedDriverDTO.getId());

        assertEquals(savedDriverDTO.getFirstname(), retrievedDriverDTO.getFirstname());
        assertEquals(savedDriverDTO.getId(), retrievedDriverDTO.getId());
    }

    @Test
    void testUpdateDriver() {
        DriverDTO savedDriverDTO = driverService.saveDriver(driverDTO);

        savedDriverDTO.setFirstname("Updated Name");
        DriverDTO updatedDriverDTO = driverService.updateDriver(savedDriverDTO.getId(), savedDriverDTO);

        assertEquals("Updated Name", updatedDriverDTO.getFirstname());

        Driver driver = driverRepository.findById(savedDriverDTO.getId()).orElseThrow(null);
        assertEquals("Updated Name", driver.getFirstname());
    }

    @Test
    void testDeleteDriver() {
        DriverDTO savedDriverDTO = driverService.saveDriver(driverDTO);

        driverService.deleteDriver(savedDriverDTO.getId());

        assertFalse(driverRepository.existsById(savedDriverDTO.getId()));
    }
}
