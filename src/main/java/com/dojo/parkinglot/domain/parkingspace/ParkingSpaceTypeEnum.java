package com.dojo.parkinglot.domain.parkingspace;

public enum ParkingSpaceTypeEnum {
    ELECTRIC {
        @Override
        public String toString() {
           return "ELECTRIC";
        }
    },
    GENERIC {
        @Override
        public String toString() {
           return "GENERIC";
        }
    }
}
