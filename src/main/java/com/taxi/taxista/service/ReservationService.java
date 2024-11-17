package com.taxi.taxista.service;

import com.taxi.taxista.DTO.ReservationAnalyticsDTO;
import com.taxi.taxista.DTO.ReservationDTO;
import com.taxi.taxista.entity.Reservation;
import com.taxi.taxista.mapper.ReservationMapper;
import com.taxi.taxista.repository.CustomerRepository;
import com.taxi.taxista.repository.DriverRepository;
import com.taxi.taxista.repository.ReservationRepository;
import com.taxi.taxista.repository.VehiculeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final CustomerRepository customerRepository;
    private final DriverRepository driverRepository;
    private final VehiculeRepository vehiculeRepository;



    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ReservationDTO> getReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::toDto);
    }

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = ReservationDTO.toEntity(reservationDTO);
        Reservation savedReservation = reservationRepository.save(reservation);
        return Reservation.toDto(savedReservation);
    }

    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        if (!reservationRepository.existsById(id)) {
            return null;
        }
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        reservation.setId(id);
        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(savedReservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    // Analytics logic
    public ReservationAnalyticsDTO getReservationAnalytics() {
        ReservationAnalyticsDTO analytics = new ReservationAnalyticsDTO();

        // Total reservations
        long totalReservations = reservationRepository.count();
        analytics.setTotalReservations(totalReservations);

        // Reservations by status
        Map<String, Long> reservationsByStatus = reservationRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        reservation -> reservation.getStatut().name(), // Assuming `status` is an Enum
                        Collectors.counting()
                ));
        analytics.setReservationsByStatus(reservationsByStatus);

        // Reservations by vehicle type
        Map<String, Long> reservationsByVehiculeType = reservationRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        reservation -> reservation.getVehiculeType().name(), // Assuming `vehiculeType` is an Enum
                        Collectors.counting()
                ));
        analytics.setReservationsByVehiculeType(reservationsByVehiculeType);

        return analytics;
    }
}