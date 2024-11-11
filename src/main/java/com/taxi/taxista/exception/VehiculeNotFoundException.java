package com.taxi.taxista.exception;

public class VehiculeNotFoundException extends RuntimeException{

    public VehiculeNotFoundException(Long id) {
        super("Vehicule not found with ID: " + id);
    }
}
