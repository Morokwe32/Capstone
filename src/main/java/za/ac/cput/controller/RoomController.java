package za.ac.cput.controller;

/**RoomController.java
 *Controller class for Room
 *Author: Jade John Arendse
 *Student Number: 220282188
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.PriceGroup;
import za.ac.cput.domain.Room;
import za.ac.cput.service.impl.RoomServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomServiceImpl roomService;

    @GetMapping("/list/all")
    public ResponseEntity<List<Room>> getRooms() {
        List<Room> allRooms = roomService.getAll();
        return ResponseEntity.ok(allRooms);
    }

    @GetMapping("/list/economy")
    public ResponseEntity<List<Room>> getEconomyRooms() {
        List<Room> economyRooms = roomService.getAll().stream()
                .filter(room -> room.getPriceGroup() == PriceGroup.ECONOMY)
                .collect(Collectors.toList());
        return ResponseEntity.ok(economyRooms);
    }

    @GetMapping("/list/luxury")
    public ResponseEntity<List<Room>> getLuxuryRooms() {
        List<Room> luxuryRooms = roomService.getAll().stream()
                .filter(room -> room.getPriceGroup() == PriceGroup.LUXURY)
                .collect(Collectors.toList());
        return ResponseEntity.ok(luxuryRooms);
    }

    @GetMapping("/list/special")
    public ResponseEntity<List<Room>> getSpecialRooms() {
        List<Room> specialRooms = roomService.getAll().stream()
                .filter(room -> room.getPriceGroup() == PriceGroup.SPECIAL)
                .collect(Collectors.toList());
        return ResponseEntity.ok(specialRooms);
    }

    @GetMapping("/list/available/all")
    public ResponseEntity<List<Room>> getAllAvailableRooms() {
        List<Room> availableRooms = roomService.getAllAvailableRooms();
        return ResponseEntity.ok(availableRooms);
    }

    @GetMapping("/list/available/economy")
    public ResponseEntity<List<Room>> getAvailableEconomyRooms() {
        List<Room> availableEconomyRooms = roomService.getAllAvailableRooms().stream()
                .filter(room -> room.getPriceGroup() == PriceGroup.ECONOMY)
                .collect(Collectors.toList());
        return ResponseEntity.ok(availableEconomyRooms);
    }


    @GetMapping("/list/available/special")
    public ResponseEntity<List<Room>> getAvailableSpecialRooms() {
        List<Room> availableSpecialRooms = roomService.getAllAvailableRooms().stream()
                .filter(room -> room.getPriceGroup() == PriceGroup.SPECIAL)
                .collect(Collectors.toList());
        return ResponseEntity.ok(availableSpecialRooms);
    }

    @GetMapping("/read/{roomId}")
    public ResponseEntity<Room> readRoom(@PathVariable Integer roomId) {
        Room readRoom = roomService.read(roomId);
        if (readRoom != null) {
            return ResponseEntity.ok(readRoom);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Room createdRoom = roomService.create(room);
        return ResponseEntity.ok(createdRoom);
    }
}
