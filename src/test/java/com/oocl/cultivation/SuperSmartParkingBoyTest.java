package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SuperSmartParkingBoyTest {
    @Test
    void should_park_at_first_car_park_when_park_given_a_car_and_super_smart_parking_boy_with_two_parking_lots_when_first_has_larger_available_position_rate()
        throws NotEnoughPositionException {
        // GIVEN
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(3);
        parkingLot2.park(new Car());
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();

        // WHEN
        Ticket ticket = superSmartParkingBoy.park(car);

        // THEN
        assertNotNull(ticket);
        assertTrue(parkingLot1.isInParkingLot(ticket));
        assertEquals(car, parkingLot1.getTicketCarHashMap().get(ticket));
    }
}
