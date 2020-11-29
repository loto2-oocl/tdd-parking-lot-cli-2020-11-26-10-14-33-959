package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;

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

    public Ticket parkWithAssignedParkingBoy(Car car, ParkingBoy parkingBoy) throws NotEnoughPositionException {
        return parkingBoy.park(car);
    }

    public Car fetchCarWithAssignedParkingBoy(Ticket ticket, ParkingBoy parkingBoy) {
        return null;
    }
}
