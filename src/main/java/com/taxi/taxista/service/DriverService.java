package com.taxi.taxista.service;

import com.taxi.taxista.DTO.DriverDTO;
import com.taxi.taxista.entity.Driver;
import com.taxi.taxista.entity.Reservation;
import com.taxi.taxista.entity.enums.DriverStatus;
import com.taxi.taxista.entity.enums.ReservationStatus;
import com.taxi.taxista.mapper.DriverMapper;
import com.taxi.taxista.repository.DriverRepository;
import com.taxi.taxista.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Validated
public class DriverService {
    private static final Logger logger = LoggerFactory.getLogger(DriverService.class);
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    private final  ReservationRepository reservationRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository, DriverMapper driverMapper, ReservationRepository reservationRepository) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
        this.reservationRepository = reservationRepository;
    }

    public DriverDTO saveDriver(@Valid DriverDTO driverDTO) {

        Driver driver = driverMapper.toEntity(driverDTO);
        Driver savedDriver = driverRepository.save(driver);

        return driverMapper.toDTO(savedDriver);
    }

    public DriverDTO updateDriver(Long id, @Valid DriverDTO updatedDriverDTO) {
        Driver existingDriver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found: " + id));
        driverMapper.updateEntityFromDTO(updatedDriverDTO, existingDriver);
        existingDriver.setId(id);
        Driver savedDriver = driverRepository.save(existingDriver);
        return driverMapper.toDTO(savedDriver);
    }

    public List<DriverDTO> getAllDrivers() {
        try {
            return driverRepository.findAll()
                    .stream()
                    .map(driverMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error retrieving drivers: {}", e.getMessage(), e);
            throw e;
        }
    }

    public DriverDTO getDriverById(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found: " + id));
        return driverMapper.toDTO(driver);
    }

    public void deleteDriver(Long id) {
        if (!driverRepository.existsById(id)) {
            throw new RuntimeException("Driver not found: " + id);
        }
        driverRepository.deleteById(id);
    }

    public double calculateOccupancyPercentage(List<Driver> allDrivers) {
        long totalNumberOfDrivers = allDrivers.size();
        long driversCurrentlyOnTrip = allDrivers.stream()
                .filter(driver -> driver.getStatus() == DriverStatus.ON_TRIP)
                .count();

        if (totalNumberOfDrivers == 0) {
            return 0;
        }
        double occupancyPercentage = (double) driversCurrentlyOnTrip / totalNumberOfDrivers * 100;
        return occupancyPercentage;
    }

    public Map<String, LocalTime[]> calculateAvailabilityTimeSlots(List<Driver> drivers) {
        Map<String, LocalTime[]> availabilityTimeSlots = new HashMap<>();

        for (Driver driver : drivers) {
            String driverName = driver.getFirstname();
            LocalTime[] availability = { driver.getAvailabilityStart(), driver.getAvailabilityEnd()};
            availabilityTimeSlots.put(driverName, availability);
        }

        return availabilityTimeSlots;
    }
    public Map<String, Long> calculateDriverStatusDistribution(List<Driver> drivers) {
        Map<String, Long> statusDistribution = new HashMap<>();

        for (Driver driver : drivers) {
            DriverStatus status = driver.getStatus();

            statusDistribution.put(status.name(),
                    statusDistribution.getOrDefault(status.name(), 0L) + 1);
        }

        return statusDistribution;
    }
}
