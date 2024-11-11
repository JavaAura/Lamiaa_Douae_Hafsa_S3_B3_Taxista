package com.taxi.taxista.entity.enums;

public enum VehiculeType {

        SEDAN,
        VAN,
        MINIBUS;

        public static VehiculeType fromString(String type) {
                for (VehiculeType v : VehiculeType.values()) {
                        if (v.name().equalsIgnoreCase(type)) {
                                return v;
                        }
                }
                throw new IllegalArgumentException("Unknown enum type " + type);
        }
}