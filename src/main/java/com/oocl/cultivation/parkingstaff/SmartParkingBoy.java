package com.oocl.cultivation.parkingstaff;

import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.strategy.parking.SmartParkingStrategy;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.setParkingStrategy(new SmartParkingStrategy());
    }
}
