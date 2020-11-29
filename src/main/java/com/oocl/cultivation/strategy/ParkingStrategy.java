package com.oocl.cultivation.strategy;

import com.oocl.cultivation.ParkingLot;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingLot> findAvailableParkingLot(List<ParkingLot> parkingLots);
}
