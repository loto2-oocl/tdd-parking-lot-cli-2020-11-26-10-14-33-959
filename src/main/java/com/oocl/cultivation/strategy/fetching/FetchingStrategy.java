package com.oocl.cultivation.strategy.fetching;

import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.Ticket;

import java.util.List;
import java.util.Optional;

public interface FetchingStrategy {
    Optional<ParkingLot> getCarParkedParkingLot(Ticket ticket, List<ParkingLot> parkingLots);
}
