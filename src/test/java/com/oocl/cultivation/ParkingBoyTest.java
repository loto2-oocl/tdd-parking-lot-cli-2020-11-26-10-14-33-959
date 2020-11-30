package com.oocl.cultivation;

import com.oocl.cultivation.parkingstaff.ParkingBoy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ParkingBoyTest {
    @Test
    void should_return_true_when_has_available_parking_lot_given_a_parking_boy_and_parking_lot_with_10_remaining_capacity() {
        // GIVEN
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        when(parkingLot.getRemainingCapacity()).thenReturn(10);
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>(Arrays.asList(parkingLot)));

        // WHEN
        boolean actual = parkingBoy.hasAvailableParkingLot();

        // THEN
        assertEquals(true, actual);
    }
}
