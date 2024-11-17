package com.taxi.taxista.exception;

public class DriverNotFoundException  extends RuntimeException{

    public DriverNotFoundException(Long id) {
        super("Driver not found with ID: " + id);
    }
}
