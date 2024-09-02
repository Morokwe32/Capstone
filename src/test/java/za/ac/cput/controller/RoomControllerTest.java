/*
package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import za.ac.cput.domain.PriceGroup;
import za.ac.cput.domain.Room;
import za.ac.cput.service.impl.RentalServiceImpl;
import za.ac.cput.service.impl.RoomServiceImpl;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private RentalServiceImpl rentalService;

    private Room roomEconomy;
    private Room roomLuxury;
    private Room roomSpecial;

    @BeforeEach
    public void setUp() {
        roomEconomy = new Room(1, "Room1", "Economy", 100.0, true, 2, "ECONOMY", PriceGroup.ECONOMY);
        roomLuxury = new Room(2, "Room2", "Luxury", 200.0, true, 2, "LUXURY", PriceGroup.LUXURY);
        roomSpecial = new Room(3, "Room3", "Special", 300.0, true, 2, "SPECIAL", PriceGroup.SPECIAL);

        // You can use an in-memory database or set up in-memory repositories for these services if needed
        roomService.saveAll(Arrays.asList(roomEconomy, roomLuxury, roomSpecial));
        rentalService.setRooms(Arrays.asList(roomEconomy, roomLuxury, roomSpecial));
    }

    @Test
    public void testGetRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/list/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceGroup").value("ECONOMY"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].priceGroup").value("LUXURY"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].priceGroup").value("SPECIAL"));
    }

    @Test
    public void testGetEconomyRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/room/list/economy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceGroup").value("ECONOMY"));
    }

    @Test
    public void testGetLuxuryRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/room/list/luxury")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceGroup").value("LUXURY"));
    }

    @Test
    public void testGetSpecialRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/list/special")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceGroup").value("SPECIAL"));
    }

    @Test
    public void testGetAllAvailableRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/list/available/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceGroup").value("ECONOMY"));
    }

    @Test
    public void testGetAvailableEconomyRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/list/available/economy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceGroup").value("ECONOMY"));
    }

    @Test
    public void testGetAvailableLuxuryRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/list/available/luxury")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceGroup").value("LUXURY"));
    }

    @Test
    public void testGetAvailableSpecialRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/list/available/special")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceGroup").value("SPECIAL"));
    }

    @Test
    public void testReadRoom() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/read/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceGroup").value("ECONOMY"));
    }
}
*/