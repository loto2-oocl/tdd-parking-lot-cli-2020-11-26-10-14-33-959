package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.parkingstaff.ParkingLotServiceManager;
import com.oocl.cultivation.parkingstaff.StandardParkingBoy;
import com.oocl.cultivation.strategy.fetching.StandardFetchingStrategy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ParkingLotServiceManagerTest {
    @Test
    void should_append_one_parking_boy_to_managed_parking_boy_list_when_append_parking_boy_given_a_service_manager_and_a_parking_boy() {
        // GIVEN
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(new ArrayList<>());
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>());

        // WHEN
        parkingLotServiceManager.appendParkingBoy(standardParkingBoy);

        // THEN
        assertTrue(parkingLotServiceManager.getManagedParkingBoys().contains(standardParkingBoy));
    }

    @Test
    void should_assigned_parking_boy_call_park_once_when_park_with_assigned_parking_boy_given_a_car_and_a_manager_and_a_parking_boy()
        throws NotEnoughPositionException {
        // GIVEN
        StandardParkingBoy standardParkingBoy = Mockito.mock(StandardParkingBoy.class);
        when(standardParkingBoy.hasAvailableParkingLot()).thenReturn(true);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>());
        parkingLotServiceManager.appendParkingBoy(standardParkingBoy);
        Car car = new Car();

        // WHEN
        parkingLotServiceManager.parkWithAssignedParkingBoy(car);

        // Then
        verify(standardParkingBoy, times(1)).park(car);
    }

    @Test
    void should_assigned_parking_boy_call_fetch_once_when_fetch_car_with_assigned_parking_boy_given_a_ticket_and_a_manager_and_a_parking_boy()
        throws UnrecognizedParkingTicketException {
        // GIVEN
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        StandardParkingBoy standardParkingBoy = Mockito.mock(StandardParkingBoy.class);

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>());
        parkingLotServiceManager.appendParkingBoy(standardParkingBoy);
        Ticket ticket = new Ticket();

        when(standardParkingBoy.getFetchingStrategy()).thenReturn(new StandardFetchingStrategy());
        when(standardParkingBoy.getParkingLots()).thenReturn(Collections.singletonList(parkingLot));
        when(parkingLot.isInParkingLot(ticket)).thenReturn(true);

        // WHEN
        parkingLotServiceManager.fetchCarWithAssignedParkingBoy(ticket);

        // Then
        verify(standardParkingBoy, times(1)).fetchCar(ticket);
    }

    @Test
    void should_throw_not_enough_position_exception_when_park_with_assigned_parking_boy_given_a_car_a_manager_and_parking_boy() {
        // GIVEN
        ParkingLot parkingLot1 = new ParkingLot(0);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>());
        parkingLotServiceManager.appendParkingBoy(standardParkingBoy);

        // THEN
        assertThrows(
            NotEnoughPositionException.class,
            () -> {
                // WHEN
                parkingLotServiceManager.parkWithAssignedParkingBoy(new Car());
            }
            , "Not Enough Position."
        );
    }

    @Test
    void should_throw_unrecognized_ticket_exception_when_fetch_car_with_assigned_parking_boy_given_a_fake_ticket_a_manager_and_parking_boy() {
        // GIVEN
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        List<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot));
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>());
        parkingLotServiceManager.appendParkingBoy(standardParkingBoy);
        Ticket fakeTicket = new Ticket();
        when(parkingLot.isInParkingLot(fakeTicket)).thenReturn(false);

        // THEN
        assertThrows(
            UnrecognizedParkingTicketException.class,
            () -> {
                // WHEN
                parkingLotServiceManager.fetchCarWithAssignedParkingBoy(fakeTicket);
            }
            , "Unrecognized parking ticket."
        );
    }
}
