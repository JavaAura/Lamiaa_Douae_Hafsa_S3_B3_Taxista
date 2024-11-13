package com.taxi.taxista.service;

import com.taxi.taxista.DTO.DriverDTO;
import com.taxi.taxista.entity.Driver;
import com.taxi.taxista.mapper.DriverMapper;
import com.taxi.taxista.repository.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class DriverService {
    private static final Logger logger = LoggerFactory.getLogger(DriverService.class);
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Autowired
    public DriverService(DriverRepository driverRepository, DriverMapper driverMapper) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
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
}
