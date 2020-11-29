package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.List;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        ParkingLot availableParkingLot = this.parkingLots.stream()
            .filter(parkingLot -> !parkingLot.isParkingLotFull())
            .findFirst().orElse(null);

        if (availableParkingLot == null) {
            throw new NotEnoughPositionException();
        }

        return availableParkingLot.park(car);
    }

    public Car fetchCar(Ticket ticket) throws UnrecognizedParkingTicketException {
        return this.parkingLots.get(0).fetchCar(ticket);
    }
}
