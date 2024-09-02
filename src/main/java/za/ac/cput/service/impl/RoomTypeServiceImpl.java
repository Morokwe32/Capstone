package za.ac.cput.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.RoomType;
import za.ac.cput.repository.RoomTypeRepository;

import java.util.List;

@Service
public class RoomTypeServiceImpl {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    public RoomType getRoomTypeById(Integer id) {
        return roomTypeRepository.findById(id).orElse(null);
    }

    public RoomType saveRoomType(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    public void deleteRoomType(Integer id) {
        roomTypeRepository.deleteById(id);
    }
}

