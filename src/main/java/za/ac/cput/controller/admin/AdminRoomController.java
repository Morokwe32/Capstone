package za.ac.cput.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Room;
import za.ac.cput.domain.PriceGroup;
import za.ac.cput.service.impl.RoomServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/rooms")
public class AdminRoomController {

    @Autowired
    private RoomServiceImpl roomService;

    @GetMapping("/all")
    public List<Room> getRooms() {
        List<Room> allRooms = new ArrayList<>(roomService.getAll());
        return allRooms;
    }
    @PostMapping("/create")
    public Room createRoom(@RequestBody Room room) {
        System.out.println("/api/admin/rooms/create was triggered");
        room.setPriceGroup(mapPriceGroup(room.getPriceGroupString()));
        System.out.println("PriceGroup was set to: " + room.getPriceGroup());
        System.out.println("RoomService was created...attempting to create room...");
        Room createdRoom = roomService.create(room);
        return createdRoom;
    }

    @GetMapping("/read/{roomId}")
    public Room readRoom(@PathVariable Integer roomId) {
        System.out.println("/api/admin/rooms/read was triggered");
        System.out.println("RoomService was created...attempting to read room...");
        Room readRoom = roomService.read(roomId);
        return readRoom;
    }

    @PutMapping("/update/{roomId}")
    public Room updateRoom(@PathVariable int roomId, @RequestBody Room updatedRoom) {
        Room updated = roomService.update(updatedRoom);
        return updated;
    }

    @DeleteMapping("/delete/{roomId}")
    public boolean deleteRoom(@PathVariable Integer roomId) {
        System.out.println("/api/admin/rooms/delete was triggered");
        System.out.println("RoomService was created...attempting to delete room...");
        return roomService.delete(roomId);
    }

    private PriceGroup mapPriceGroup(String priceGroupString) {
        try {
            return PriceGroup.valueOf(priceGroupString);
        } catch (IllegalArgumentException e) {
            return PriceGroup.NONE;
        }
    }

}
//.delete(`http://localhost:8080/api/admin/rooms/delete/${roomId}`)