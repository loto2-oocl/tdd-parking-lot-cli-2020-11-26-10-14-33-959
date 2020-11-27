package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    @Test
    void should_return_one_parking_ticket_when_park_the_car_given_a_car_and_enough_capacity() {
        // GIVEN
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);

        // WHEN
        Ticket ticket = parkingLot.park(car);

        // THEN
        assertNotNull(ticket);
    }

    @Test
    void should_parked_one_car_with_one_ticket_when_park_the_car_given_a_car_and_enough_capacity() {
        // GIVEN
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(10);

        // WHEN
        Ticket ticket = parkingLot.park(car);

        // THEN
        assertTrue(parkingLot.getCarTicketHashMap().containsKey(car));
        assertEquals(ticket, parkingLot.getCarTicketHashMap().get(car));
    }
}