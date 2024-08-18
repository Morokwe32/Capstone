package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.RoomType;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTypeFactoryTest
{

    @Test
    void buildRoomTypeSuccessfully()
    {
        RoomType roomType = RoomTypeFactory.createRoomType(1L, "Deluxe", 150.00);
        assertNotNull(roomType);
        assertEquals(1L, roomType.getTypeId());
        assertEquals("Deluxe", roomType.getRoomtypeName());
        assertEquals(150.00, roomType.getRoomPrice());
        System.out.println(roomType);
    }

    @Test
    void buildRoomTypeFailsWhenNameIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RoomTypeFactory.createRoomType(1L, "", 150.00);
        });
        assertEquals("Room type name is required.", exception.getMessage());
    }

    @Test
    void buildRoomTypeFailsWhenPriceIsNegative()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RoomTypeFactory.createRoomType(1L, "Deluxe", -150.00);
        });
        assertEquals("Room price must be positive.", exception.getMessage());
    }
/*
    @Test
    void buildGeneratedId() {
        RoomType roomType = RoomTypeFactory.createRoomType(null, "Standard", 100.00);
        assertNotNull(roomType);
        assertTrue(roomType.getTypeId() > 0);
        assertEquals("Standard", roomType.getRoomtypeName());
        assertEquals(100.00, roomType.getRoomPrice());
        System.out.println(roomType);
    }*/

}
