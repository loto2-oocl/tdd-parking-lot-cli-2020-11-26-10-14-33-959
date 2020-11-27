package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {
    private final Integer capacity;
    private final HashMap<Ticket, Car> carTicketHashMap;

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
        this.carTicketHashMap = new HashMap<Ticket, Car>();
    }

    public HashMap<Ticket, Car> getCarTicketHashMap() {
        return carTicketHashMap;
    }

    public Ticket park(Car car) {
        if (this.isParkingLotFull()) {
            return null;
        }

        if (this.getCarTicketHashMap().containsValue(car)) {
            return null;
        }

        Ticket ticket = new Ticket();
        carTicketHashMap.put(ticket, car);
        return ticket;
    }

    public Car fetchCar(Ticket ticket) {
        return null;
    }

    public boolean isParkingLotFull() {
        return (capacity - this.carTicketHashMap.size()) <= 0;
    }
}
