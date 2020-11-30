package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.parkingstaff.SuperSmartParkingBoy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class SuperSmartParkingBoyTest {
    @Test
    void should_park_at_first_car_park_when_park_given_a_car_and_super_smart_parking_boy_with_two_parking_lots_when_first_has_larger_available_position_rate()
        throws NotEnoughPositionException {
        // GIVEN
        ParkingLot parkingLot1 = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLot2 = Mockito.mock(ParkingLot.class);
        List<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1, parkingLot2));
        when(parkingLot1.isParkingLotAvailable()).thenReturn(true);
        when(parkingLot2.isParkingLotAvailable()).thenReturn(true);
        when(parkingLot1.getAvailablePositionRate()).thenReturn(0.5);
        when(parkingLot2.getAvailablePositionRate()).thenReturn(0.1);

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();

        // WHEN
        superSmartParkingBoy.park(car);

        // THEN
        verify(parkingLot1, times(1)).park(car);
    }
}
