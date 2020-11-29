package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    @Test
    void should_park_at_second_car_park_when_park_given_a_car_and_smart_parking_boy_with_two_parking_lots_when_second_has_more_capacity()
        throws NotEnoughPositionException {
        // GIVEN
        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        // WHEN
        Ticket ticket = smartParkingBoy.park(car);

        // THEN
        assertNotNull(ticket);
        assertTrue(parkingLot2.isInParkingLot(ticket));
        assertEquals(car, parkingLot2.getTicketCarHashMap().get(ticket));
    }
}
