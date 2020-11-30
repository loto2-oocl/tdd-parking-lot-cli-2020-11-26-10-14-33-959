package com.oocl.cultivation.parkingstaff;

import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.strategy.parking.StandardParkingStrategy;

import java.util.List;

public class StandardParkingBoy extends ParkingBoy {
    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.setParkingStrategy(new StandardParkingStrategy());
    }
}
