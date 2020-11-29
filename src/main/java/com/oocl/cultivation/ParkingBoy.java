package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        Optional<ParkingLot> availableParkingLot = this.findAvailableParkingLot();

        if (!availableParkingLot.isPresent()) {
            throw new NotEnoughPositionException();
        }

        return availableParkingLot.get().park(car);
    }

    private Optional<ParkingLot> findAvailableParkingLot() {
        return this.parkingLots.stream()
            .filter(ParkingLot::isParkingLotAvaialble)
            .findFirst();
    }

    public Car fetchCar(Ticket ticket) throws UnrecognizedParkingTicketException {
        Optional<ParkingLot> targetedParkingLot = this.parkingLots.stream()
            .filter(parkingLot -> parkingLot.isInParkingLot(ticket))
            .findFirst();

        return targetedParkingLot.get().fetchCar(ticket);
    }
}
