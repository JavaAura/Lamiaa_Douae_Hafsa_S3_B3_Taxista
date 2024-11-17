package com.taxi.taxista.service;

import com.taxi.taxista.DTO.ReservationAnalyticsDTO;
import com.taxi.taxista.DTO.ReservationDTO;
import com.taxi.taxista.entity.Customer;
import com.taxi.taxista.entity.Driver;
import com.taxi.taxista.entity.Reservation;
import com.taxi.taxista.entity.Vehicule;
import com.taxi.taxista.mapper.ReservationMapper;
import com.taxi.taxista.repository.CustomerRepository;
import com.taxi.taxista.repository.DriverRepository;
import com.taxi.taxista.repository.ReservationRepository;
import com.taxi.taxista.repository.VehiculeRepository;
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
        Optional<Customer> customer = customerRepository.findById(reservationDTO.getCustomerId());
        Optional<Driver> driver = driverRepository.findById(reservationDTO.getDriverId());
        Optional<Vehicule> vehicule = vehiculeRepository.findById(reservationDTO.getVehiculeId());
        if(customer.isPresent() && vehicule.isPresent() && driver.isPresent()){
            reservation.setCustomer(customer.get());
            reservation.setDriver(driver.get());
            reservation.setVehicule(vehicule.get());
        };
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

    public ReservationAnalyticsDTO getReservationAnalytics() {
        ReservationAnalyticsDTO analytics = new ReservationAnalyticsDTO();

        long totalReservations = reservationRepository.count();
        analytics.setTotalReservations(totalReservations);

        Map<String, Long> reservationsByStatus = reservationRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        reservation -> reservation.getStatus().name(),
                        Collectors.counting()
                ));
        analytics.setReservationsByStatus(reservationsByStatus);

        return analytics;
    }
}