package com.taxi.taxista.vehicule;

import com.taxi.taxista.DTO.VehiculeDTO;
import com.taxi.taxista.entity.Vehicule;
import com.taxi.taxista.entity.enums.VehiculeStatus;
import com.taxi.taxista.entity.enums.VehiculeType;
import com.taxi.taxista.exception.VehiculeNotFoundException;
import com.taxi.taxista.mapper.VehiculeMapper;
import com.taxi.taxista.repository.VehiculeRepository;
import com.taxi.taxista.service.VehiculeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class VehiculeServiceTest {

    @Mock
    private VehiculeRepository vehiculeRepository;

    @Mock
    private VehiculeMapper vehiculeMapper;

    @InjectMocks
    private VehiculeService vehiculeService;

    private Vehicule vehicule;
    private VehiculeDTO vehiculeDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup vehicule entity
        vehicule = new Vehicule();
        vehicule.setId(1L);
        vehicule.setModel("Model 1");
        vehicule.setRegistrationNumber("ABC-123");
        vehicule.setMileage(10000);
        vehicule.setStatus(VehiculeStatus.AVAILABLE);
        vehicule.setType(VehiculeType.BERLINE);

        // Setup vehicule DTO
        vehiculeDTO = new VehiculeDTO();
        vehiculeDTO.setId(1L);
        vehiculeDTO.setModel("Model 1");
        vehiculeDTO.setRegistrationNumber("ABC-123");
        vehiculeDTO.setMileage(10000);
        vehiculeDTO.setStatus(VehiculeStatus.AVAILABLE);
        vehiculeDTO.setType(VehiculeType.BERLINE);
    }

    @Test
    void testSaveVehicule() {
        when(vehiculeMapper.toEntity(any(VehiculeDTO.class))).thenReturn(vehicule);
        when(vehiculeRepository.save(any(Vehicule.class))).thenReturn(vehicule);
        when(vehiculeMapper.toDTO(any(Vehicule.class))).thenReturn(vehiculeDTO);

        VehiculeDTO result = vehiculeService.saveVehicule(vehiculeDTO);

        assertNotNull(result);
        assertEquals(vehiculeDTO.getId(), result.getId());
        assertEquals(vehiculeDTO.getModel(), result.getModel());
        verify(vehiculeRepository, times(1)).save(vehicule);
    }

    @Test
    void testFindAll() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Vehicule> vehiculePage = new PageImpl<>(Arrays.asList(vehicule));
        when(vehiculeRepository.findAll(pageRequest)).thenReturn(vehiculePage);
        when(vehiculeMapper.toDTO(any(Vehicule.class))).thenReturn(vehiculeDTO);

        Page<VehiculeDTO> result = vehiculeService.findAll(0, 5);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(vehiculeDTO.getId(), result.getContent().get(0).getId());
        verify(vehiculeRepository, times(1)).findAll(pageRequest);
    }

    @Test
    void testGetVehiculeByIdFound() {
        when(vehiculeRepository.findById(anyLong())).thenReturn(Optional.of(vehicule));
        when(vehiculeMapper.toDTO(any(Vehicule.class))).thenReturn(vehiculeDTO);

        Optional<VehiculeDTO> result = vehiculeService.getVehiculeById(1L);

        assertTrue(result.isPresent());
        assertEquals(vehiculeDTO.getId(), result.get().getId());
        verify(vehiculeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetVehiculeByIdNotFound() {
        when(vehiculeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(VehiculeNotFoundException.class, () -> vehiculeService.getVehiculeById(1L));
        verify(vehiculeRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteVehicule() {
        when(vehiculeRepository.existsById(anyLong())).thenReturn(true);

        vehiculeService.deleteVehicule(1L);

        verify(vehiculeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteVehiculeNotFound() {
        when(vehiculeRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(VehiculeNotFoundException.class, () -> vehiculeService.deleteVehicule(1L));
        verify(vehiculeRepository, times(0)).deleteById(1L);
    }

    @Test
    void testUpdateVehicule() {
        when(vehiculeRepository.findById(anyLong())).thenReturn(Optional.of(vehicule));
        when(vehiculeRepository.save(any(Vehicule.class))).thenReturn(vehicule);
        when(vehiculeMapper.toDTO(any(Vehicule.class))).thenReturn(vehiculeDTO);

        VehiculeDTO result = vehiculeService.updateVehicule(1L, vehiculeDTO);

        assertNotNull(result);
        assertEquals(vehiculeDTO.getId(), result.getId());
        verify(vehiculeRepository, times(1)).save(vehicule);
    }

    @Test
    void testUpdateVehiculeNotFound() {
        when(vehiculeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(VehiculeNotFoundException.class, () -> vehiculeService.updateVehicule(1L, vehiculeDTO));
        verify(vehiculeRepository, times(0)).save(any(Vehicule.class));
    }

    @Test
    void testGetVehiculesByType() {
        List<Vehicule> vehicules = Arrays.asList(vehicule);
        when(vehiculeRepository.findByType(any(VehiculeType.class))).thenReturn(vehicules);
        when(vehiculeMapper.toDTO(any(Vehicule.class))).thenReturn(vehiculeDTO);

        List<VehiculeDTO> result = vehiculeService.getVehiculesByType(VehiculeType.BERLINE);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(vehiculeDTO.getId(), result.get(0).getId());
        verify(vehiculeRepository, times(1)).findByType(VehiculeType.BERLINE);
    }
}
