package za.ac.cput.repository;

import za.ac.cput.domain.RoomType;

public interface iRepository
{
    RoomType create(RoomType roomType);
    RoomType findById(Long id);
    RoomType update(RoomType roomType);
    void delete(RoomType roomType);
}
