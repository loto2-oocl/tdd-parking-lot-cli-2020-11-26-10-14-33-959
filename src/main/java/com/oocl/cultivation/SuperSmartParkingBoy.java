package com.oocl.cultivation;

import java.util.List;
import java.util.Optional;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Optional<ParkingLot> findAvailableParkingLot() {
        return super.findAvailableParkingLot();
    }
}
