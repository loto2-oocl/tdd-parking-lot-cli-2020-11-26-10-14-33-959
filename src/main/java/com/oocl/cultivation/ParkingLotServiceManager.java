package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    private final List<ParkingBoy> managedParkingBoys;

    public ParkingLotServiceManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.managedParkingBoys = new ArrayList<>();
    }

    public ParkingLotServiceManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        super(parkingLots);
        this.managedParkingBoys = parkingBoys;
    }

    public List<ParkingBoy> getManagedParkingBoys() {
        return managedParkingBoys;
    }

    public void appendParkingBoy(ParkingBoy parkingBoy) {
        this.getManagedParkingBoys().add(parkingBoy);
    }
}
