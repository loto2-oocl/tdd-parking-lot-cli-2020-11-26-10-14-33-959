package com.oocl.cultivation;

import com.oocl.cultivation.strategy.SuperSmartParkingStrategy;

import java.util.List;

public class SuperSmartParkingBoy extends StandardParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.setParkingStrategy(new SuperSmartParkingStrategy());
    }
}
