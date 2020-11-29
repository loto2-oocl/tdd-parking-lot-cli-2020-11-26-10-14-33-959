package com.oocl.cultivation;

import com.oocl.cultivation.strategy.SmartParkingStrategy;

import java.util.List;

public class SmartParkingBoy extends StandardParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.setParkingStrategy(new SmartParkingStrategy());
    }
}
