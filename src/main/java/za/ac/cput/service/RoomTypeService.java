package za.ac.cput.service;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.RoomType;
import za.ac.cput.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RoomTypeService implements IRoomTypeService
{
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomTypeService(RoomTypeRepository roomTypeRepository)
    {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public RoomType create(RoomType roomType)
    {
        return roomTypeRepository.create(roomType);
    }

    @Override
    public RoomType read(Long id)
    {
        return roomTypeRepository.findById(id);
    }

    @Override
    public RoomType update(RoomType roomType)
    {
        RoomType existingRoomType = roomTypeRepository.findById(roomType.getTypeId());
        if (existingRoomType != null)
        {
            return roomTypeRepository.update(roomType);
        }
        return null;
    }

    @Override
    public void delete(RoomType roomType)
    {
        roomTypeRepository.delete(roomType);
    }
}
