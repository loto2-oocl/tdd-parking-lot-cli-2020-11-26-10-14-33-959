package com.oocl.cultivation;

import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.HashMap;

public class ParkingLot {
    private final Integer capacity;
    private final HashMap<Ticket, Car> ticketCarHashMap;

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
        this.ticketCarHashMap = new HashMap<Ticket, Car>();
    }

    public HashMap<Ticket, Car> getTicketCarHashMap() {
        return ticketCarHashMap;
    }

    public Ticket park(Car car) {
        if (this.isParkingLotFull()) {
            return null;
        }

        if (this.getTicketCarHashMap().containsValue(car)) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticketCarHashMap.put(ticket, car);
        return ticket;
    }

    public Car fetchCar(Ticket ticket) throws UnrecognizedParkingTicketException {
        if(ticket.isUsed()) {
            throw new UnrecognizedParkingTicketException();
        }

        if (ticketCarHashMap.containsKey(ticket)) {
            ticket.setUsed();
            return ticketCarHashMap.remove(ticket);
        }

        throw new UnrecognizedParkingTicketException();
    }

    public boolean isParkingLotFull() {
        return (capacity - this.ticketCarHashMap.size()) <= 0;
    }
}
