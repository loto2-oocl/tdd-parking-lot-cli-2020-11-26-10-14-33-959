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

public class ParkingLotServiceManager extends ParkingBoy {
    private final List<ParkingBoy> managedParkingBoys;

    public ParkingLotServiceManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.setParkingStrategy(new StandardParkingStrategy());
        this.managedParkingBoys = new ArrayList<>();
    }

    public List<ParkingBoy> getManagedParkingBoys() {
        return this.managedParkingBoys;
    }

    public void appendParkingBoy(ParkingBoy parkingBoy) {
        this.getManagedParkingBoys().add(parkingBoy);
    }

    public Ticket parkWithAssignedParkingBoy(Car car) throws NotEnoughPositionException {
        Optional<ParkingBoy> assignedParkingBoy = this.getManagedParkingBoys().stream()
            .filter(ParkingBoy::hasAvailableParkingLot)
            .findAny();

        if (assignedParkingBoy.isPresent()) {
            return assignedParkingBoy.get().park(car);
        }

        throw new NotEnoughPositionException();
    }

    public Car fetchCarWithAssignedParkingBoy(Ticket ticket) throws UnrecognizedParkingTicketException {
        Optional<ParkingBoy> assignedParkingBoy = this.getManagedParkingBoys().stream()
            .filter(parkingBoy -> parkingBoy.getFetchingStrategy().getCarParkedParkingLot(ticket, parkingBoy.getParkingLots()).isPresent())
            .findFirst();

        if (assignedParkingBoy.isPresent()) {
            return assignedParkingBoy.get().fetchCar(ticket);
        }

        throw new UnrecognizedParkingTicketException();
    }
}
