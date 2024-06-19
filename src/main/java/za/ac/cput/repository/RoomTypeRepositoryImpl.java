package za.ac.cput.repository;

import za.ac.cput.domain.RoomType;
import java.util.HashMap;
import java.util.Map;

public class RoomTypeRepositoryImpl implements RoomTypeRepository
{
    private Map<Long, RoomType> roomTypeMap = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public RoomType create(RoomType roomType)
    {
        if (roomType.getTypeId() == 0)
        {
            roomType = new RoomType.Builder()
                    .copy(roomType)
                    .setTypeId(idCounter++)
                    .build();
        }
        roomTypeMap.put(roomType.getTypeId(), roomType);
        return roomType;
    }

    @Override
    public RoomType findById(Long id)
    {
        return roomTypeMap.get(id);
    }

    @Override
    public RoomType update(RoomType roomType)
    {
        if (roomTypeMap.containsKey(roomType.getTypeId()))
        {
            roomTypeMap.put(roomType.getTypeId(), roomType);
            return roomType;
        } else {
            throw new RuntimeException("RoomType not found");
        }
    }

    @Override
    public void delete(RoomType roomType)
    {
        roomTypeMap.remove(roomType.getTypeId());
    }
}
