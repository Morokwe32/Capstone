package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.RoomType;
import za.ac.cput.service.impl.RoomTypeServiceImpl;

import java.util.List;
import java.util.Optional;


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

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getRoomImage(@PathVariable Integer id) {
        RoomType roomType = roomTypeService.getRoomTypeById(Math.toIntExact(id));

        // Check if the room is present
        if (roomType.getType().getBytes() == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found if no game or image is available
        }

        // Retrieve the image from the game object
        byte[] image = roomType.getPicture();

        // Return the image data with the appropriate content type
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust to MediaType.IMAGE_PNG if the image is in PNG format
                .body(image);
    }

    @PostMapping("/create")
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
