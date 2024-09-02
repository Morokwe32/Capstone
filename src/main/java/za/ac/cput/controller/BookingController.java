package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Room;
import za.ac.cput.service.IRoomService;
import za.ac.cput.service.impl.BookingServiceImpl;

import java.util.List;

 // Replace with your frontend URL
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;

    @Autowired
    private IRoomService roomService;

    // Create a new booking
    @PostMapping("/create")
    public ResponseEntity<String> createBooking(@RequestBody Booking bookingRequest) {
        try {
            bookingService.create(bookingRequest);
            return ResponseEntity.ok("Booking created successfully");
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating booking");
        }
    }

    // Read a booking by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Booking> readBooking(@PathVariable Integer id) {
        Booking booking = bookingService.read(id);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Update an existing booking by ID
    @PutMapping("/update/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Integer bookingId, @RequestBody Booking booking) {
        Booking updatedBooking = bookingService.updateById(bookingId, booking);
        if (updatedBooking != null) {
            return ResponseEntity.ok(updatedBooking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


     // Delete a booking by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Integer id) {
        boolean deleted = bookingService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Booking deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get a list of all rooms
    @GetMapping("/rooms/all")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAll();
        return ResponseEntity.ok(rooms);
    }
}
