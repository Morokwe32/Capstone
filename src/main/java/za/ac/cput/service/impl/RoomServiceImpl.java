package za.ac.cput.service.impl;

/*
 *RoomServiceImpl.java
 *RoomServiceImpl class for Room
 *Author: Jade John Arendse
 *Student Number: 220282188
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Room;
import za.ac.cput.factory.RoomFactory;
import za.ac.cput.repository.RoomRepository;
import za.ac.cput.service.IRoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("roomServiceImpl")
public class RoomServiceImpl implements IRoomService {

    @Autowired
    private RoomRepository repository;
    @Autowired
    private RoomFactory roomFactory;


    public RoomServiceImpl(RoomRepository repository) {

        this.repository = repository;
    }

    @Override
    public Room create(Room room) {
        room.setAvailable(true);
        Room newRoom = roomFactory.create(room);
        return repository.save(newRoom);

    }

    @Override
    public Room read(Integer id) {
        //optional
        Optional<Room> optionalRoom = this.repository.findById(id);
        return optionalRoom.orElse(null);
    }

    @Override
    public Room update(Room room) {
        if (this.repository.existsById((int) room.getId())) {
            Room updatedRoom = roomFactory.create(room);
            return this.repository.save(updatedRoom);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return true;
        }

        return false;

    }

    @Override
    public ArrayList<Room> getAll() {

        ArrayList<Room> all = (ArrayList<Room>) this.repository.findAll();
        return all;
    }

    //get all available rooms
    public ArrayList<Room> getAllAvailableRooms() {
        List<Room> availableRoom = new ArrayList<>();
        List<Room> rooms = getAll();  // Renamed 'room' to 'rooms'
        for (Room room : rooms) {  // Now the loop variable is 'room'
            if (room.isAvailable()) {
                availableRoom.add(room);
            }
        }
        return (ArrayList<Room>) availableRoom;
    }

    //check if room is available
    public boolean isRoomAvailable(Room room) {
        return room.isAvailable();
    }



}

