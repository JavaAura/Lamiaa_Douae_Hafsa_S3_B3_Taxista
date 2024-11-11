package com.taxi.taxista.controller;

import com.taxi.taxista.DTO.VehiculeDTO;
import com.taxi.taxista.entity.enums.VehiculeType;
import com.taxi.taxista.service.VehiculeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    @Autowired
    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @PostMapping
    @Operation(summary = "Create a new vehicule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehicule created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid vehicule data")
    })
    public ResponseEntity<VehiculeDTO> create(
            @Parameter(description = "Vehicule details to create", required = true) @Valid @RequestBody VehiculeDTO dto) {
        VehiculeDTO createdVehicule = vehiculeService.saveVehicule(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicule);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicule by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicule retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicule not found")
    })
    public ResponseEntity<VehiculeDTO> getById(
            @Parameter(description = "ID of the vehicule to retrieve", required = true) @PathVariable Long id) {
        return vehiculeService.getVehiculeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all vehicules with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicules retrieved successfully")
    })
    public ResponseEntity<Page<VehiculeDTO>> getAll(
            @Parameter(description = "Page number", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size", example = "10") @RequestParam(defaultValue = "10") int size) {
        Page<VehiculeDTO> vehicules = vehiculeService.findAll(page, size);
        return ResponseEntity.ok(vehicules);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a vehicule by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vehicule deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicule not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the vehicule to delete", required = true) @PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a vehicule by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicule updated successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicule not found"),
            @ApiResponse(responseCode = "400", description = "Invalid vehicule data")
    })
    public ResponseEntity<VehiculeDTO> updateVehicule(
            @Parameter(description = "ID of the vehicule to update", required = true) @PathVariable Long id,
            @Parameter(description = "Updated vehicule details", required = true) @Valid @RequestBody VehiculeDTO updatedVehiculeDTO) {
        VehiculeDTO updatedVehicule = vehiculeService.updateVehicule(id, updatedVehiculeDTO);
        return ResponseEntity.ok(updatedVehicule);
    }

    @GetMapping("/search/type")
    @Operation(summary = "Search vehicles by type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicles retrieved successfully")
    })
    public ResponseEntity<List<VehiculeDTO>> getVehiculesByType(
            @RequestParam VehiculeType type) {
        List<VehiculeDTO> vehicles = vehiculeService.getVehiculesByType(type);
        return ResponseEntity.ok(vehicles);
    }

}
