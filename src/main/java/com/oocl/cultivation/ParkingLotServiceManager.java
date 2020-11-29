package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends StandardParkingBoy {
    private final List<StandardParkingBoy> managedParkingBoys;

    public ParkingLotServiceManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.managedParkingBoys = new ArrayList<>();
    }

    public List<StandardParkingBoy> getManagedParkingBoys() {
        return managedParkingBoys;
    }

    public void appendParkingBoy(StandardParkingBoy parkingBoy) {
        this.getManagedParkingBoys().add(parkingBoy);
    }

    public Ticket parkWithAssignedParkingBoy(Car car, StandardParkingBoy parkingBoy) throws NotEnoughPositionException {
        return parkingBoy.park(car);
    }

    public Car fetchCarWithAssignedParkingBoy(Ticket ticket, StandardParkingBoy parkingBoy) throws UnrecognizedParkingTicketException {
        return parkingBoy.fetchCar(ticket);
    }
}
