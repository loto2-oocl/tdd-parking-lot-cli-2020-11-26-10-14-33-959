package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.parkingstaff.StandardParkingBoy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class StandardParkingBoyTest {
    @Test
    void should_parking_boy_call_parking_lot_park_function_when_park_the_car_given_parking_boy_with_one_parking_lot()
        throws NotEnoughPositionException {
        // Given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        when(parkingLot.isParkingLotAvailable()).thenReturn(true);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();

        // When
        standardParkingBoy.park(car);

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
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Ticket ticket = new Ticket();
        when(parkingLot.isInParkingLot(ticket)).thenReturn(true);

        // WHEN
        standardParkingBoy.fetchCar(ticket);

        // THEN
        verify(parkingLot, times(1)).fetchCar(ticket);
    }

    @Test
    void should_car_parked_at_first_parking_lot_when_park_car_given_a_car_and_parking_boy_with_two_parking_lot_at_enough_capacity()
        throws NotEnoughPositionException {
        // GIVEN
        ParkingLot parkingLot1 = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLot2 = Mockito.mock(ParkingLot.class);
        List<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        when(parkingLot1.isParkingLotAvailable()).thenReturn(true);
        when(parkingLot2.isParkingLotAvailable()).thenReturn(true);

        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();

        // WHEN
        standardParkingBoy.park(car);

        // THEN
        verify(parkingLot1, times(1)).park(car);
    }

    @Test
    void should_car_parked_at_second_parking_lot_when_park_car_given_a_car_and_parking_boy_with_two_parking_lot_with_only_second_has_enough_capacity()
        throws NotEnoughPositionException {
        // GIVEN
        ParkingLot parkingLot1 = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLot2 = Mockito.mock(ParkingLot.class);
        List<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        when(parkingLot1.isParkingLotAvailable()).thenReturn(false);
        when(parkingLot2.isParkingLotAvailable()).thenReturn(true);

        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();

        // WHEN
        standardParkingBoy.park(car);

        // THEN
        verify(parkingLot2, times(1)).park(car);
    }

    @Test
    void should_throw_not_enough_position_exception_when_park_car_give_a_car_and_parking_boy_with_two_parking_lot_with_not_enough_capacity() {
        // GIVEN
        ParkingLot parkingLot1 = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLot2 = Mockito.mock(ParkingLot.class);
        List<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        when(parkingLot1.isParkingLotAvailable()).thenReturn(false);
        when(parkingLot2.isParkingLotAvailable()).thenReturn(false);

        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);

        // THEN
        assertThrows(
            NotEnoughPositionException.class,
            () -> {
                // WHEN
                standardParkingBoy.park(new Car());
            }
            , "Not enough position."
        );
    }

    @Test
    void should_fetch_car_from_second_car_park_when_fetch_car_given_a_ticket_and_parking_boy_with_two_parking_lot_and_one_parked_the_target_car()
        throws UnrecognizedParkingTicketException {
        // GIVEN
        Ticket ticket = new Ticket();
        Car car = new Car();
        ParkingLot parkingLot1 = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLot2 = Mockito.mock(ParkingLot.class);
        List<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        when(parkingLot1.isInParkingLot(ticket)).thenReturn(false);
        when(parkingLot2.isInParkingLot(ticket)).thenReturn(true);

        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);

        // WHEN
        standardParkingBoy.fetchCar(ticket);

        // THEN
        verify(parkingLot2, times(1)).fetchCar(ticket);
    }

    @Test
    void should_throw_unrecognized_ticket_exception_when_fetch_car_given_a_fake_ticket_and_parking_boy_with_two_empty_parking_lots() {
        // GIVEN
        Ticket fakeTicket = new Ticket();

        ParkingLot parkingLot1 = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLot2 = Mockito.mock(ParkingLot.class);
        List<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        when(parkingLot1.isInParkingLot(fakeTicket)).thenReturn(false);
        when(parkingLot2.isInParkingLot(fakeTicket)).thenReturn(false);

        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);

        // THEN
        assertThrows(
            UnrecognizedParkingTicketException.class,
            () -> {
                // WHEN
                standardParkingBoy.fetchCar(fakeTicket);
            }
            , "Unrecognized parking ticket."
        );

    }
}

