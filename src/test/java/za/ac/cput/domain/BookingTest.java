package za.ac.cput.domain;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookingTest {

    @Test
    public void testBooking() {
        // Create a sample User, Room, and LocalDateTime objects for testing
        User user = new User();
        Room room = new Room();
        LocalDateTime inDate = LocalDateTime.now();
        LocalDateTime outDate = LocalDateTime.now();

        // Create a Booking object using the Builder
        Booking booking = Booking.builder()
                .user(user)
                .room(room)
                .InDate(inDate)
                .OutDate(outDate)
                .build();

        // Perform assertions
        assertNotNull(booking);
        assertEquals(user, booking.getUser());
        assertEquals(room, booking.getRoom());
        assertEquals(inDate, booking.getInDate());
        assertEquals(outDate, booking.getOutDate());
    }
}
