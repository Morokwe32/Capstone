package za.ac.cput.repository;

import za.ac.cput.domain.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomTypeRepositoryTest
{
/*
    @Autowired
    private TestEntityManager entityManager;
*/
    //@Autowired
    private RoomTypeRepository roomTypeRepository;

    @BeforeEach
    public void setUp()
    {
        roomTypeRepository = new RoomTypeRepositoryImpl();
    }


    @Test
    public void testCreateRoomType()
    {
        RoomType roomType = new RoomType.Builder()
                .setRoomtypeName("Standard")
                .setRoomPrice(100.0)
                .build();

        RoomType savedRoomType = roomTypeRepository.create(roomType);

        assertNotNull(savedRoomType);
        assertNotNull(savedRoomType.getTypeId());
        assertEquals(roomType.getRoomtypeName(), savedRoomType.getRoomtypeName());
        assertEquals(roomType.getRoomPrice(), savedRoomType.getRoomPrice());
    }

    @Test
    public void testFindById()
    {
        RoomType roomType = new RoomType.Builder()
                .setRoomtypeName("Deluxe")
                .setRoomPrice(150.0)
                .build();

        RoomType savedRoomType = roomTypeRepository.create(roomType);
        RoomType foundRoomType = roomTypeRepository.findById(savedRoomType.getTypeId());

        assertNotNull(foundRoomType);
        assertEquals(savedRoomType.getTypeId(), foundRoomType.getTypeId());
        assertEquals(savedRoomType.getRoomtypeName(), foundRoomType.getRoomtypeName());
        assertEquals(savedRoomType.getRoomPrice(), foundRoomType.getRoomPrice());
    }


    @Test
    public void testUpdateRoomType()
    {
        RoomType roomType = new RoomType.Builder()
                .setRoomtypeName("Economy")
                .setRoomPrice(80.0)
                .build();

        RoomType savedRoomType = roomTypeRepository.create(roomType);
        savedRoomType = new RoomType.Builder()
                .copy(savedRoomType)
                .setRoomtypeName("Standard")
                .build();

        RoomType updatedRoomType = roomTypeRepository.update(savedRoomType);

        assertNotNull(updatedRoomType);
        assertEquals(savedRoomType.getTypeId(), updatedRoomType.getTypeId());
        assertEquals(savedRoomType.getRoomtypeName(), updatedRoomType.getRoomtypeName());
        assertEquals(savedRoomType.getRoomPrice(), updatedRoomType.getRoomPrice());
    }


    @Test
    public void testDeleteRoomType()
    {
        RoomType roomType = new RoomType.Builder()
                .setRoomtypeName("Suite")
                .setRoomPrice(200.0)
                .build();

        RoomType savedRoomType = roomTypeRepository.create(roomType);
        roomTypeRepository.delete(savedRoomType);
        RoomType deletedRoomType = roomTypeRepository.findById(savedRoomType.getTypeId());

        assertNull(deletedRoomType);
    }
/*
    @Test
    public void whenDeleteRoomType_thenRoomTypeShouldNotExist() {
        // Given
        RoomType roomType = new RoomType.Builder()
                .setRoomtypeName("Family Room")
                .setRoomPrice(200.0)
                .build();
        entityManager.persist(roomType);
        entityManager.flush();

        // When
        roomTypeRepository.delete(roomType);
        entityManager.flush();

        // Then
        RoomType deletedRoomType = entityManager.find(RoomType.class, roomType.getTypeId());
        assertThat(deletedRoomType).isNull();
    }*/
}
