package za.ac.cput.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.service.RoomTypeService;
import za.ac.cput.domain.RoomType;

@Component
public class RoomTypeAPI
{
    private final RoomTypeService roomTypeService;

    @Autowired
    public RoomTypeAPI(RoomTypeService roomTypeService)
    {
        this.roomTypeService = roomTypeService;
    }

    public RoomType create(RoomType roomType)
    {
        return roomTypeService.create(roomType);
    }

    public RoomType read(Long id)
    {
        return roomTypeService.read(id);
    }

    public RoomType update(RoomType roomType)
    {
        return roomTypeService.update(roomType);
    }

    public void delete(Long id)
    {
        RoomType roomType = roomTypeService.read(id);
        if (roomType != null)
        {
            roomTypeService.delete(roomType);
        }
    }
}
