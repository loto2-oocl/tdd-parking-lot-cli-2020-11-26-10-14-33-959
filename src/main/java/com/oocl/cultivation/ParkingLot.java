package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {
    private final Integer capacity;
    private final HashMap<Car, Ticket> carTicketHashMap;

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
        this.carTicketHashMap = new HashMap<Car, Ticket>();
    }

    public HashMap<Car, Ticket> getCarTicketHashMap() {
        return carTicketHashMap;
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        carTicketHashMap.put(car, ticket);
        return ticket;
    }
}
