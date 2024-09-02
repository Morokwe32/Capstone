package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.RoomType;
import za.ac.cput.service.impl.RoomTypeServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/api/roomtypes")
public class RoomTypeController {

    @Autowired
    private RoomTypeServiceImpl roomTypeService;

    @GetMapping
    public List<RoomType> getAllRoomTypes() {
        return roomTypeService.getAllRoomTypes();
    }

    @GetMapping("/{id}")
    public RoomType getRoomTypeById(@PathVariable Integer id) {
        return roomTypeService.getRoomTypeById(id);
    }

    @PostMapping
    public RoomType createRoomType(@RequestBody RoomType roomType) {
        return roomTypeService.saveRoomType(roomType);
    }

    @PutMapping("/{id}")
    public RoomType updateRoomType(@PathVariable Integer id, @RequestBody RoomType roomType) {
        roomType.setId(id);
        return roomTypeService.saveRoomType(roomType);
    }

    @DeleteMapping("/{id}")
    public void deleteRoomType(@PathVariable Integer id) {
        roomTypeService.deleteRoomType(id);
    }

}
