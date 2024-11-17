package com.taxi.taxista.DTO;

import java.util.Map;

public class ReservationAnalyticsDTO {
    private long totalReservations;
    private Map<String, Long> reservationsByStatus;

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

}
