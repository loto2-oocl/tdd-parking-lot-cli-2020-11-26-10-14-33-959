package com.oocl.cultivation.parkingstaff;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.Ticket;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.strategy.StandardParkingStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    private final List<ParkingBoy> managedParkingBoys;

    public ParkingLotServiceManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.setParkingStrategy(new StandardParkingStrategy());
        this.managedParkingBoys = new ArrayList<>();
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

    public Car fetchCarWithAssignedParkingBoy(Ticket ticket, ParkingBoy parkingBoy) throws UnrecognizedParkingTicketException {
        return parkingBoy.fetchCar(ticket);
    }
}
