package com.oocl.cultivation;

import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

public class ParkingBoy {
    private final ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) {
        return this.parkingLot.park(car);
    }

    public Car fetchCar(Ticket ticket) throws UnrecognizedParkingTicketException {
        return this.parkingLot.fetchCar(ticket);
    }
}
