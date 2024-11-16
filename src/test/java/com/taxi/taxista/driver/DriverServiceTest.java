package com.taxi.taxista.driver;

import com.taxi.taxista.DTO.DriverDTO;
import com.taxi.taxista.entity.Driver;
import com.taxi.taxista.entity.enums.DriverStatus;
import com.taxi.taxista.exception.DriverNotFoundException;
import com.taxi.taxista.mapper.DriverMapper;
import com.taxi.taxista.repository.DriverRepository;
import com.taxi.taxista.repository.ReservationRepository;
import com.taxi.taxista.service.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
public class DriverServiceTest {
    @Mock
    private DriverRepository driverRepository;

    @Mock
    private DriverMapper driverMapper;

    @InjectMocks
    private DriverService driverService;

    private Driver driver;
    private DriverDTO driverDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup driver entity
        driver = new Driver();
        driver.setId(1L);
        driver.setFirstname("John");
        driver.setLastname("Doe");
        driver.setStatus(DriverStatus.AVAILABLE);
        driver.setAvailabilityStart(LocalTime.of(8, 0));
        driver.setAvailabilityEnd(LocalTime.of(16, 0));

        // Setup driver DTO
        driverDTO = new DriverDTO();
        driverDTO.setId(1L);
        driverDTO.setFirstname("John");
        driverDTO.setLastname("Doe");
        driverDTO.setStatus(DriverStatus.AVAILABLE);
        driverDTO.setAvailabilityStart(LocalTime.of(8, 0));
        driverDTO.setAvailabilityEnd(LocalTime.of(16, 0));
    }

    @Test
    void testSaveDriver() {
        when(driverMapper.toEntity(any(DriverDTO.class))).thenReturn(driver);
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);
        when(driverMapper.toDTO(any(Driver.class))).thenReturn(driverDTO);

        DriverDTO result = driverService.saveDriver(driverDTO);

        assertNotNull(result);
        assertEquals(driverDTO.getId(), result.getId());
        assertEquals(driverDTO.getFirstname(), result.getFirstname());
        verify(driverRepository, times(1)).save(driver);
    }

    @Test
    void testGetAllDrivers() {
        when(driverRepository.findAll()).thenReturn(Arrays.asList(driver));
        when(driverMapper.toDTO(any(Driver.class))).thenReturn(driverDTO);

        List<DriverDTO> result = driverService.getAllDrivers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(driverDTO.getId(), result.get(0).getId());
        verify(driverRepository, times(1)).findAll();
    }

    @Test
    void testGetDriverByIdFound() {
        when(driverRepository.findById(anyLong())).thenReturn(Optional.of(driver));
        when(driverMapper.toDTO(any(Driver.class))).thenReturn(driverDTO);

        DriverDTO result = driverService.getDriverById(1L);

        assertNotNull(result);
        assertEquals(driverDTO.getId(), result.getId());
        verify(driverRepository, times(1)).findById(1L);
    }

    @Test
    void testGetDriverByIdNotFound() {
        when(driverRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(DriverNotFoundException.class, () -> driverService.getDriverById(1L));
        verify(driverRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateDriver() {
        when(driverRepository.findById(anyLong())).thenReturn(Optional.of(driver));
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);
        when(driverMapper.toDTO(any(Driver.class))).thenReturn(driverDTO);

        DriverDTO result = driverService.updateDriver(1L, driverDTO);

        assertNotNull(result);
        assertEquals(driverDTO.getId(), result.getId());
        verify(driverRepository, times(1)).save(driver);
    }

    @Test
    void testUpdateDriverNotFound() {
        when(driverRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(DriverNotFoundException.class, () -> driverService.updateDriver(1L, driverDTO));
        verify(driverRepository, times(0)).save(any(Driver.class));
    }

    @Test
    void testDeleteDriver() {
        when(driverRepository.existsById(anyLong())).thenReturn(true);

        driverService.deleteDriver(1L);

        verify(driverRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteDriverNotFound() {
        when(driverRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(DriverNotFoundException.class, () -> driverService.deleteDriver(1L));
        verify(driverRepository, times(0)).deleteById(1L);
    }
}
