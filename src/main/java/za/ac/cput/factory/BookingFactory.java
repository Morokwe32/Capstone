package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Room;
import za.ac.cput.domain.User;

import java.time.LocalDateTime;

public class BookingFactory {

    public static Booking createBooking(int id, User user, Room room, LocalDateTime inDate, LocalDateTime outDate) {
        return new Booking.Builder()
                .id(id)
                .user(user)
                .room(room)
                .InDate(inDate)
                .OutDate(outDate)
                .build();
    }
}

