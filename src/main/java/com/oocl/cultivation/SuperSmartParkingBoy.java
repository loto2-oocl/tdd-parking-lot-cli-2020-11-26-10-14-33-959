package com.oocl.cultivation;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Optional<ParkingLot> findAvailableParkingLot() {
        return this.parkingLots.stream()
            .filter(ParkingLot::isParkingLotAvailable).max(Comparator.comparingDouble(ParkingLot::getAvailablePositionRate));
    }
}
