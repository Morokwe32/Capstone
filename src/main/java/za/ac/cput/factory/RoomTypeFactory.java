package za.ac.cput.factory;

import za.ac.cput.util.Helper;
import za.ac.cput.domain.RoomType;
public class RoomTypeFactory
{
    public static RoomType createRoomType(long id, String typename, double price)
    {
        if(Helper.isNullOrEmpty(String.valueOf(id)) || Helper.isNullOrEmpty(typename) || Helper.isNullOrEmpty(String.valueOf(price)))
        {
            throw new IllegalArgumentException("Invalid input: None of the fields can be null or empty.");
        }
        return new RoomType.Builder()
                .setTypeId(id)
                .setRoomtypeName(typename)
                .setRoomPrice(price)
                .build();
    }
}
