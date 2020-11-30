package com.oocl.cultivation.parkingstaff;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.Ticket;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.strategy.parking.StandardParkingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLotServiceManager extends StandardParkingBoy {
    private final List<StandardParkingBoy> managedParkingBoys;

    public ParkingLotServiceManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.setParkingStrategy(new StandardParkingStrategy());
        this.managedParkingBoys = new ArrayList<>();
    }

    public List<StandardParkingBoy> getManagedParkingBoys() {
        return this.managedParkingBoys;
    }

    public void appendParkingBoy(StandardParkingBoy parkingBoy) {
        this.getManagedParkingBoys().add(parkingBoy);
    }

    public Ticket parkWithAssignedParkingBoy(Car car) throws NotEnoughPositionException {
        Optional<StandardParkingBoy> assignedParkingBoy = this.getManagedParkingBoys().stream()
            .filter(StandardParkingBoy::hasAvailableParkingLot)
            .findAny();

        if (assignedParkingBoy.isPresent()) {
            return assignedParkingBoy.get().park(car);
        }

        throw new NotEnoughPositionException();
    }

    public Car fetchCarWithAssignedParkingBoy(Ticket ticket) throws UnrecognizedParkingTicketException {
        Optional<StandardParkingBoy> assignedParkingBoy = this.getManagedParkingBoys().stream()
            .filter(parkingBoy -> parkingBoy.getFetchingStrategy().getCarParkedParkingLot(ticket, parkingBoy.getParkingLots()).isPresent())
            .findFirst();

        if (assignedParkingBoy.isPresent()) {
            return assignedParkingBoy.get().fetchCar(ticket);
        }

        throw new UnrecognizedParkingTicketException();
    }
}
