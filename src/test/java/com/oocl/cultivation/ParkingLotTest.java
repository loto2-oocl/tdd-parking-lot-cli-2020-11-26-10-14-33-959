package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    @Test
    void should_return_one_parking_ticket_when_park_the_car_given_a_car_and_enough_capacity() throws NotEnoughPositionException {
        // GIVEN
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);

        // WHEN
        Ticket ticket = parkingLot.park(car);

        // THEN
        assertNotNull(ticket);
    }

    @Test
    void should_parked_one_car_with_one_ticket_when_park_the_car_given_a_car_and_enough_capacity() throws NotEnoughPositionException {
        // GIVEN
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(10);

        // WHEN
        Ticket ticket = parkingLot.park(car);

        // THEN
        assertTrue(parkingLot.getTicketCarHashMap().containsKey(ticket));
        assertEquals(car, parkingLot.getTicketCarHashMap().get(ticket));
    }

    @Test
    void should_throw_not_enough_position_exception_when_park_car_given_a_car_and_parking_lot_not_enough_capacity()
        throws NotEnoughPositionException {
        // GIVEN
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(0);

        // THEN
        assertThrows(
            NotEnoughPositionException.class,
            () -> {
                // WHEN
                parkingLot.park(car);
            }
            , "Not enough position."
        );
    }

    @Test
    void should_multiple_cars_parked_with_ticket_when_park_car_given_multiple_cars_and_enough_capacity() throws NotEnoughPositionException {
        // GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(2);

        // WHEN
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

        // THEN
        assertTrue(parkingLot.getTicketCarHashMap().containsKey(ticket1));
        assertTrue(parkingLot.getTicketCarHashMap().containsKey(ticket2));
        assertEquals(car1, parkingLot.getTicketCarHashMap().get(ticket1));
        assertEquals(car2, parkingLot.getTicketCarHashMap().get(ticket2));
    }

    @Test
    void should_park_one_car_only_with_one_ticket_and_throw_not_enough_position_exception_when_park_car_given_two_cars_and_one_capacity()
        throws NotEnoughPositionException {
        // GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);

        // WHEN
        Ticket ticket1 = parkingLot.park(car1);

        // THEN
        assertTrue(parkingLot.getTicketCarHashMap().containsKey(ticket1));
        assertEquals(car1, parkingLot.getTicketCarHashMap().get(ticket1));

        // THEN
        assertThrows(
            NotEnoughPositionException.class,
            () -> {
                // WHEN
                parkingLot.park(car2);
            }
            , "Not enough position."
        );
    }

    @Test
    void should_return_null_when_park_car_twice_with_same_car_given_a_car_and_enough_capacity() throws NotEnoughPositionException {
        // GIVEN
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(2);

        // WHEN
        Ticket ticket1 = parkingLot.park(car);
        Ticket ticket2 = parkingLot.park(car);

        // THEN
        assertTrue(parkingLot.getTicketCarHashMap().containsKey(ticket1));
        assertEquals(car, parkingLot.getTicketCarHashMap().get(ticket1));
        assertNull(ticket2);
    }

    @Test
    void should_return_a_car_and_ticket_is_used_when_fetch_car_with_a_ticket_and_parked_in_parking_lot()
        throws UnrecognizedParkingTicketException, NotEnoughPositionException {
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

    @Test
    void should_throw_unrecognized_parking_ticket_exception_when_fetch_car_given_a_used_ticket_and_parking_lot() {
        // GIVEN
        Ticket ticket = new Ticket();
        ticket.setUsed();
        ParkingLot parkingLot = new ParkingLot(1);

        // THEN
        assertThrows(
            UnrecognizedParkingTicketException.class,
            () -> {
                // WHEN
                parkingLot.fetchCar(ticket);
            }
        );
    }

    @Test
    void should_throw_unrecognized_parking_ticket_exception_when_fetch_car_given_a_fake_ticket_and_parking_lot() {
        // GIVEN
        Ticket fakeTicket = new Ticket();
        ParkingLot parkingLot = new ParkingLot(1);

        // THEN
        assertThrows(
            UnrecognizedParkingTicketException.class,
            () -> {
                // WHEN
                parkingLot.fetchCar(fakeTicket);
            }
        );

    }
}