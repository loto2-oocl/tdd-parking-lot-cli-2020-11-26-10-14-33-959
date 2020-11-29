package com.oocl.cultivation.strategy;

import com.oocl.cultivation.ParkingLot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingLot> findAvailableParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
            .filter(ParkingLot::isParkingLotAvailable).max(Comparator.comparingInt(ParkingLot::getRemainingCapacity));
    }
}
