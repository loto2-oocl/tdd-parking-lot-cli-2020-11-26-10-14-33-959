package com.oocl.cultivation.exception;

public class UnrecognizedParkingTicketException extends Exception {
    @Override
    public String getMessage() {
        return "Unrecognized parking ticket.";
    }
}
