package com.taxi.taxista.controller;

import com.taxi.taxista.DTO.DriverDTO;

import com.taxi.taxista.entity.Driver;
import com.taxi.taxista.mapper.DriverMapper;
import com.taxi.taxista.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Slf4j
@RestController
@RequestMapping("/api/drivers")

public class DriverController {

    private final DriverService driverService;
    private final DriverMapper driverMapper;
    private static final Logger logger = LoggerFactory.getLogger(DriverService.class);

    @Autowired
    public DriverController(DriverService driverService, DriverMapper driverMapper) {
        this.driverService = driverService;
        this.driverMapper=driverMapper;
    }

    @PostMapping
    public ResponseEntity<DriverDTO> createDriver(@Valid @RequestBody DriverDTO driverDTO) {
        logger.info("Received DriverDTO: {}", driverDTO);
        DriverDTO saveddriver = driverService.saveDriver(driverDTO);
        logger.info("Saved DriverDTO: {}", saveddriver);
        return ResponseEntity.ok(saveddriver);
    }

    @GetMapping
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        List<DriverDTO> DriverDTO = driverService.getAllDrivers();
        return ResponseEntity.ok(DriverDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> getDriverById(@PathVariable Long id) {
        DriverDTO driver = driverService.getDriverById(id);
        return ResponseEntity.ok(driver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverDTO> updateDriver(
            @PathVariable Long id, @Valid @RequestBody DriverDTO updatedDriverDTO) {
        DriverDTO updateddriver = driverService.updateDriver(id, updatedDriverDTO);
        return ResponseEntity.ok(updateddriver);
    }

    @GetMapping("/analytics/occupancy")
    public ResponseEntity<Double> getOccupancyPercentage() {
        List<DriverDTO> driverDTOs = driverService.getAllDrivers();
        List<Driver> allDrivers = convertToDriverEntities(driverDTOs);
        double occupancyPercentage = driverService.calculateOccupancyPercentage(allDrivers);
        return ResponseEntity.ok(occupancyPercentage);
    }

    @GetMapping("/analytics/availability")
    public ResponseEntity<Map<String, LocalTime[]>> getAvailabilityTimeSlots() {
        List<DriverDTO> driverDTOs = driverService.getAllDrivers();
        List<Driver> drivers = convertToDriverEntities(driverDTOs);
        Map<String, LocalTime[]> availabilityTimeSlots = driverService.calculateAvailabilityTimeSlots(drivers);
        return ResponseEntity.ok(availabilityTimeSlots);
    }

    @GetMapping("/analytics/status-distribution")
    public ResponseEntity<Map<String, Long>> getDriverStatusDistribution() {
        List<DriverDTO> driverDTOs = driverService.getAllDrivers();
        List<Driver> drivers = convertToDriverEntities(driverDTOs);
        Map<String, Long> statusDistribution = driverService.calculateDriverStatusDistribution(drivers);
        return ResponseEntity.ok(statusDistribution);
    }
    private List<Driver> convertToDriverEntities(List<DriverDTO> driverDTOs) {
        return driverDTOs.stream()
                .map(driverMapper::toEntity)
                .collect(Collectors.toList());
    }
}
