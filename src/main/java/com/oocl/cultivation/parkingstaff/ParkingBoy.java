package com.oocl.cultivation.parkingstaff;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.Ticket;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.strategy.FetchingStrategy;
import com.oocl.cultivation.strategy.ParkingStrategy;
import com.oocl.cultivation.strategy.StandardFetchingStrategy;

import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    protected final List<ParkingLot> parkingLots;
    protected ParkingStrategy parkingStrategy;
    protected FetchingStrategy fetchingStrategy;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        this.setFetchingStrategy(new StandardFetchingStrategy());
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void setFetchingStrategy(FetchingStrategy fetchingStrategy) {
        this.fetchingStrategy = fetchingStrategy;
    }

    public FetchingStrategy getFetchingStrategy() {
        return fetchingStrategy;
    }

    public List<ParkingLot> getParkingLots() {
        return this.parkingLots;
    }

    public ParkingStrategy getParkingStrategy() {
        return this.parkingStrategy;
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        Optional<ParkingLot> availableParkingLot = this.getParkingStrategy().findAvailableParkingLot(this.getParkingLots());

        if (!availableParkingLot.isPresent()) {
            throw new NotEnoughPositionException();
        }

        return availableParkingLot.get().park(car);
    }

    public Car fetchCar(Ticket ticket) throws UnrecognizedParkingTicketException {
        Optional<ParkingLot> targetedParkingLot = this.getFetchingStrategy().getCarParkedParkingLot(ticket, this.getParkingLots());

        if (!targetedParkingLot.isPresent()) {
            throw new UnrecognizedParkingTicketException();
        }

        return targetedParkingLot.get().fetchCar(ticket);
    }

    public boolean hasAvailableParkingLot() {
        return this.getParkingLots().stream()
            .anyMatch(parkingLot -> parkingLot.getRemainingCapacity() > 0);
    }
}
