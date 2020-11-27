package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParkingLotTest {
    @Test
    void should_return_one_parking_ticket_when_park_the_car_given_a_car_and_enough_capacity() {
        // GIVEN
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();

        // WHEN
        Ticket ticket = parkingLot.park(car);

        // THEN
        assertNotNull(ticket);
    }

}