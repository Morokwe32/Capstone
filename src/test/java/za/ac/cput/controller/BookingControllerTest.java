package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.cput.domain.Booking;
import za.ac.cput.service.impl.BookingServiceImpl;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookingControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingServiceImpl bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    @Test
    public void readBookingTest() throws Exception {
        Integer bookingId = 1;
        Booking booking = new Booking();
        booking.setId(bookingId);

        when(bookingService.read(bookingId)).thenReturn(booking);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/admin/bookings/read/" + bookingId))
                .andExpect(status().isOk());

        verify(bookingService, times(1)).read(bookingId);
    }

    @Test
    public void deleteBookingTest() throws Exception {
        mockMvc.perform(delete("/api/admin/bookings/delete/1"))
                .andExpect(status().isNoContent());
    }

/*
    @Test
    public void deleteBookingTest() throws Exception {
        Integer bookingId = 1;

        when(bookingService.delete(bookingId)).thenReturn(true);

        mockMvc.perform(delete("/api/admin/bookings/delete/" + bookingId))
                .andExpect(status().isOk());

        verify(bookingService, times(1)).delete(bookingId);
    }
    */
}