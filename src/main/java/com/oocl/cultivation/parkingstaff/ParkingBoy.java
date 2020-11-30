package com.oocl.cultivation.parkingstaff;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.Ticket;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.strategy.ParkingStrategy;

import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    protected final List<ParkingLot> parkingLots;
    protected ParkingStrategy parkingStrategy;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public List<ParkingLot> getParkingLots() {
        return this.parkingLots;
    }

    public ParkingStrategy getParkingStrategy() {
        return this.parkingStrategy;
    }

    protected Optional<ParkingLot> getCarParkedParkingLot(Ticket ticket) {
        return this.getParkingLots().stream()
            .filter(parkingLot -> parkingLot.isInParkingLot(ticket))
            .findFirst();
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        Optional<ParkingLot> availableParkingLot = this.getParkingStrategy().findAvailableParkingLot(this.getParkingLots());

        if (!availableParkingLot.isPresent()) {
            throw new NotEnoughPositionException();
        }

        return availableParkingLot.get().park(car);
    }

    public Car fetchCar(Ticket ticket) throws UnrecognizedParkingTicketException {
        Optional<ParkingLot> targetedParkingLot = getCarParkedParkingLot(ticket);

        if (!targetedParkingLot.isPresent()) {
            throw new UnrecognizedParkingTicketException();
        }

        return targetedParkingLot.get().fetchCar(ticket);
    }
}
