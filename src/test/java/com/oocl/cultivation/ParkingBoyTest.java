package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParkingBoyTest {
    @Test
    void should_parking_boy_call_parking_lot_park_function_when_park_the_car_given_parking_boy_with_one_parking_lot()
        throws NotEnoughPositionException {
        // Given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        when(parkingLot.isParkingLotAvaialble()).thenReturn(true);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        // When
        parkingBoy.park(car);

        // Then
        verify(parkingLot, times(1)).park(car);
    }

    @Test
    void should_parking_boy_call_parking_lot_fetch_car_function_when_fetch_car_given_a_ticket_and_parking_boy_with_one_parking_lot()
        throws UnrecognizedParkingTicketException {
        // GIVEN
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket ticket = new Ticket();
        when(parkingLot.isInParkingLot(ticket)).thenReturn(true);

        // WHEN
        parkingBoy.fetchCar(ticket);

        // THEN
        verify(parkingLot, times(1)).fetchCar(ticket);
    }

    @Test
    void should_car_parked_at_first_parking_lot_when_park_car_given_a_car_and_parking_boy_with_two_parking_lot_at_enough_capacity()
        throws NotEnoughPositionException {
        // GIVEN
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        // WHEN
        Ticket ticket = parkingBoy.park(car);

        // THEN
        assertNotNull(ticket);
        assertTrue(parkingLot1.getTicketCarHashMap().containsKey(ticket));
        assertEquals(car, parkingLot1.getTicketCarHashMap().get(ticket));
    }

    @Test
    void should_car_parked_at_second_parking_lot_when_park_car_given_a_car_and_parking_boy_with_two_parking_lot_with_only_second_has_enough_capacity()
        throws NotEnoughPositionException {
        // GIVEN
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        // WHEN
        Ticket ticket = parkingBoy.park(car);

        // THEN
        assertNotNull(ticket);
        assertTrue(parkingLot2.getTicketCarHashMap().containsKey(ticket));
        assertEquals(car, parkingLot2.getTicketCarHashMap().get(ticket));
    }

    @Test
    void should_throw_not_enough_position_exception_when_park_car_give_a_car_and_parking_boy_with_two_parking_lot_with_not_enough_capacity() {
        // GIVEN
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        // THEN
        assertThrows(
            NotEnoughPositionException.class,
            () -> {
                // WHEN
                parkingBoy.park(car);
            }
            , "Not enough position."
        );
    }

    @Test
    void should_fetch_car_from_second_car_park_when_fetch_car_given_a_ticket_and_parking_boy_with_two_parking_lot_and_one_parked_the_target_car()
        throws NotEnoughPositionException, UnrecognizedParkingTicketException {
        // GIVEN
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        Ticket ticket = parkingLot2.park(car);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        // WHEN
        Car actual = parkingBoy.fetchCar(ticket);

        // THEN
        assertEquals(car, actual);
    }
}

