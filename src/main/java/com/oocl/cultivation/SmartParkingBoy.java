package com.oocl.cultivation;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Optional<ParkingLot> findAvailableParkingLot() {
        return this.parkingLots.stream()
            .filter(ParkingLot::isParkingLotAvailable).max(Comparator.comparingInt(ParkingLot::getRemainingCapacity));
    }
}
