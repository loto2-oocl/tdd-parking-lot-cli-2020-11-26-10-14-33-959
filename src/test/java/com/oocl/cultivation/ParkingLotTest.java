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

    @Test
    void should_return_null_when_park_car_given_a_car_and_parking_lot_not_enough_capacity() {
        // GIVEN
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(0);

        // WHEN
        Ticket ticket = parkingLot.park(car);

        // THEN
        assertNull(ticket);
    }

    @Test
    void should_multiple_cars_parked_with_ticket_when_park_car_given_multiple_cars_and_enough_capacity() {
        // GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(2);

        // WHEN
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

        // THEN
        assertTrue(parkingLot.getCarTicketHashMap().containsKey(car1));
        assertTrue(parkingLot.getCarTicketHashMap().containsKey(car2));
        assertEquals(ticket1, parkingLot.getCarTicketHashMap().get(car1));
        assertEquals(ticket2, parkingLot.getCarTicketHashMap().get(car2));
    }

    @Test
    void should_park_one_car_only_with_one_ticket_when_park_car_given_two_cars_and_one_capacity() {
        // GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);

        // WHEN
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

        // THEN
        assertTrue(parkingLot.getCarTicketHashMap().containsKey(car1));
        assertFalse(parkingLot.getCarTicketHashMap().containsKey(car2));
        assertEquals(ticket1, parkingLot.getCarTicketHashMap().get(car1));
        assertNull(ticket2);
    }

    @Test
    void should_return_null_when_park_car_twice_with_same_car_given_a_car_and_enough_capacity() {
        // GIVEN
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(2);

        // WHEN
        Ticket ticket1 = parkingLot.park(car);
        Ticket ticket2 = parkingLot.park(car);

        // THEN
        assertTrue(parkingLot.getCarTicketHashMap().containsKey(car));
        assertEquals(ticket1, parkingLot.getCarTicketHashMap().get(car));
        assertNull(ticket2);
    }

    @Test
    void should_return_a_car_and_ticket_is_used_when_fetch_car_with_a_ticket_and_parked_in_parking_lot() {
        // GIVEN
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = parkingLot.park(car);

        // WHEN
        Car actualCar = parkingLot.fetchCar(ticket);

        // THEN
        assertEquals(car, actualCar);
        assertTrue(ticket.isUsed());
    }
}