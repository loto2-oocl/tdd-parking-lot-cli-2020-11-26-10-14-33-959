package com.oocl.cultivation.strategy;

import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.Ticket;

import java.util.List;
import java.util.Optional;

public class StandardFetchingStrategy implements FetchingStrategy {
    @Override
    public Optional<ParkingLot> getCarParkedParkingLot(Ticket ticket, List<ParkingLot> parkingLots) {
        return parkingLots.stream()
            .filter(parkingLot -> parkingLot.isInParkingLot(ticket))
            .findFirst();
    }
}
