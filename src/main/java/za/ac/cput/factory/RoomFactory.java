package za.ac.cput.factory;

/*
 *RoomFactory.java
 *Factory class for Room page
 *Author: Jade John Arendse
 *Student Number: 220282188
 * */

import org.springframework.stereotype.Component;
import za.ac.cput.domain.PriceGroup;
import za.ac.cput.domain.Room;

@Component
public class RoomFactory implements IFactory<Room>
{
    public Room createRoom(int id, String roomType, int capacity, PriceGroup priceGroup, boolean isAvailable) {
        return Room.builder()
                .id(id)
                .roomType(roomType)
                .capacity(capacity)
                .priceGroup(priceGroup)
                .available(isAvailable)
                .build();
    }

    @Override
    public Room create() {
        return Room.builder().build();
    }

    public Room create(Room room) {
        return Room.builder()
                .copy(room)
                .build();
    }
}

