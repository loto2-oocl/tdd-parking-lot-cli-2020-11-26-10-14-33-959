package com.oocl.cultivation.strategy.parking;

import com.oocl.cultivation.ParkingLot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperSmartParkingStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingLot> findAvailableParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
            .filter(ParkingLot::isParkingLotAvailable).max(Comparator.comparingDouble(ParkingLot::getAvailablePositionRate));
    }
}
