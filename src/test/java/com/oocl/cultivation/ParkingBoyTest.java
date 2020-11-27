package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.zip.CRC32;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ParkingBoyTest {
    @Test
    void should_parking_boy_call_parking_lot_park_function_when_park_the_car_given_parking_boy_with_parking_lot() {
        // Given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        // When
        parkingBoy.park(car);

        // Then
        verify(parkingLot, times(1)).park(car);
    }
}

