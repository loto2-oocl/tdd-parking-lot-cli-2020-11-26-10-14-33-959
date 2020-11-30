package com.oocl.cultivation.parkingstaff;

import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.strategy.parking.SuperSmartParkingStrategy;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.setParkingStrategy(new SuperSmartParkingStrategy());
    }
}
