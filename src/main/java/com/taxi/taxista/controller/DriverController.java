package com.taxi.taxista.controller;

import com.taxi.taxista.DTO.CustomerDTO;
import com.taxi.taxista.DTO.DriverDTO;
import com.taxi.taxista.service.CustomerService;
import com.taxi.taxista.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@Validated
public class DriverController {

    private final DriverService driverService;
    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<DriverDTO> createDriver(@Valid @RequestBody DriverDTO driverDTO) {
        DriverDTO saveddriver = driverService.saveDriver(driverDTO);
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
}
