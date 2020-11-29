package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ParkingLotServiceManagerTest {
    @Test
    void should_append_one_parking_boy_to_managed_parking_boy_list_when_append_parking_boy_given_a_service_manager_and_a_parking_boy() {
        // GIVEN
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>());
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>());

        // WHEN
        parkingLotServiceManager.appendParkingBoy(parkingBoy);

        // THEN
        assertTrue(parkingLotServiceManager.getManagedParkingBoys().contains(parkingBoy));
    }

    @Test
    void should_assigned_parking_boy_call_park_once_when_park_with_assigned_parking_boy_given_a_car_and_a_manager_and_a_parking_boy()
        throws NotEnoughPositionException {
        // GIVEN
        ParkingBoy parkingBoy = Mockito.mock(ParkingBoy.class);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>());
        Car car = new Car();

        // WHEN
        parkingLotServiceManager.parkWithAssignedParkingBoy(car, parkingBoy);

        // Then
        verify(parkingBoy, times(1)).park(car);
    }

    @Test
    void should_assigned_parking_boy_call_fetch_once_when_fetch_car_with_assigned_parking_boy_given_a_ticket_and_a_manager_and_a_parking_boy()
        throws UnrecognizedParkingTicketException {
        // GIVEN
        ParkingBoy parkingBoy = Mockito.mock(ParkingBoy.class);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>());
        Ticket ticket = new Ticket();

        // WHEN
        parkingLotServiceManager.fetchCarWithAssignedParkingBoy(ticket, parkingBoy);

        // Then
        verify(parkingBoy, times(1)).fetchCar(ticket);
    }
}
