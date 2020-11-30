package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.parkingstaff.SmartParkingBoy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class SmartParkingBoyTest {
    @Test
    void should_park_at_second_car_park_when_park_given_a_car_and_smart_parking_boy_with_two_parking_lots_when_second_has_more_capacity()
        throws NotEnoughPositionException {
        // GIVEN
        ParkingLot parkingLot1 = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLot2 = Mockito.mock(ParkingLot.class);
        List<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        when(parkingLot1.isParkingLotAvailable()).thenReturn(true);
        when(parkingLot2.isParkingLotAvailable()).thenReturn(true);
        when(parkingLot1.getRemainingCapacity()).thenReturn(10);
        when(parkingLot2.getRemainingCapacity()).thenReturn(100);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        // WHEN
        smartParkingBoy.park(car);

        // THEN
        verify(parkingLot2, times(1)).park(car);
    }
}
