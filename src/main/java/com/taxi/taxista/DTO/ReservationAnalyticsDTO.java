package com.taxi.taxista.DTO;

import java.util.Map;

public class ReservationAnalyticsDTO {
    private long totalReservations;
    private Map<String, Long> reservationsByStatus;
    private Map<String, Long> reservationsByVehiculeType;

    // Getters and setters
    public long getTotalReservations() {
        return totalReservations;
    }

    public void setTotalReservations(long totalReservations) {
        this.totalReservations = totalReservations;
    }

    public Map<String, Long> getReservationsByStatus() {
        return reservationsByStatus;
    }

    public void setReservationsByStatus(Map<String, Long> reservationsByStatus) {
        this.reservationsByStatus = reservationsByStatus;
    }

    public Map<String, Long> getReservationsByVehiculeType() {
        return reservationsByVehiculeType;
    }

    public void setReservationsByVehiculeType(Map<String, Long> reservationsByVehiculeType) {
        this.reservationsByVehiculeType = reservationsByVehiculeType;
    }
}
