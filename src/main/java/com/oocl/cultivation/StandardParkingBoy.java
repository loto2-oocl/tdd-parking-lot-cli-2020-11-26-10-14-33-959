package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.strategy.ParkingStrategy;
import com.oocl.cultivation.strategy.StandardParkingStrategy;

import java.util.List;
import java.util.Optional;

public class StandardParkingBoy {
    protected final List<ParkingLot> parkingLots;
    protected ParkingStrategy parkingStrategy;

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        this.setParkingStrategy(new StandardParkingStrategy());
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        Optional<ParkingLot> availableParkingLot = this.parkingStrategy.findAvailableParkingLot(this.getParkingLots());

        if (!availableParkingLot.isPresent()) {
            throw new NotEnoughPositionException();
        }

        return availableParkingLot.get().park(car);
    }

    public Car fetchCar(Ticket ticket) throws UnrecognizedParkingTicketException {
        Optional<ParkingLot> targetedParkingLot = this.parkingLots.stream()
            .filter(parkingLot -> parkingLot.isInParkingLot(ticket))
            .findFirst();

        if (!targetedParkingLot.isPresent()) {
            throw new UnrecognizedParkingTicketException();
        }

        return targetedParkingLot.get().fetchCar(ticket);
    }
}
