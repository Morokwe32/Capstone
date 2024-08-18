package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.RoomType;
import za.ac.cput.service.RoomTypeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RoomTypeControllerTest {

    @Mock
    private RoomTypeService roomTypeService;

    @InjectMocks
    private RoomTypeController roomTypeController;

    private RoomType roomType;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        roomType = new RoomType.Builder()
                .setTypeId(1L)
                .setRoomtypeName("Deluxe")
                .setRoomPrice(200.0)
                .build();
    }

    @Test
    void createRoomType() {
        when(roomTypeService.create(any(RoomType.class))).thenReturn(roomType);

        RoomType createdRoomType = roomTypeController.create(roomType);

        assertEquals(roomType, createdRoomType);
        verify(roomTypeService, times(1)).create(roomType);
    }

    @Test
    void readRoomType() {
        when(roomTypeService.read(anyLong())).thenReturn(roomType);

        RoomType foundRoomType = roomTypeController.read(1L);

        assertEquals(roomType, foundRoomType);
        verify(roomTypeService, times(1)).read(1L);
    }

    @Test
    void updateRoomType() {
        when(roomTypeService.update(any(RoomType.class))).thenReturn(roomType);

        RoomType updatedRoomType = roomTypeController.update(roomType);

        assertEquals(roomType, updatedRoomType);
        verify(roomTypeService, times(1)).update(roomType);
    }

    @Test
    void deleteRoomType() {
        when(roomTypeService.read(anyLong())).thenReturn(roomType);
        doNothing().when(roomTypeService).delete(any(RoomType.class));

        roomTypeController.delete(1L);

        verify(roomTypeService, times(1)).read(1L);
        verify(roomTypeService, times(1)).delete(roomType);
    }

    @Test
    void deleteRoomType_NotFound() {
        when(roomTypeService.read(anyLong())).thenReturn(null);

        roomTypeController.delete(1L);

        verify(roomTypeService, times(1)).read(1L);
        verify(roomTypeService, never()).delete(any(RoomType.class));
    }
}
