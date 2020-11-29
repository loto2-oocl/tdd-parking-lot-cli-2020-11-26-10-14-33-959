package com.oocl.cultivation.strategy;

import com.oocl.cultivation.ParkingLot;

import java.util.List;
import java.util.Optional;

public class StandardParkingStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingLot> findAvailableParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
            .filter(ParkingLot::isParkingLotAvailable)
            .findFirst();
    }
}
