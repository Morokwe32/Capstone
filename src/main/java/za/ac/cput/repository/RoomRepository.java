package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.cput.domain.PriceGroup;
import za.ac.cput.domain.Room;

/*
 *RoomRepo.java
 *Repo class for Room page
 *Author: Jade John Arendse
 *Student Number: 220282188
 * */

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer>
{
    //List<Room> findByPriceGroupAndRentalsOutDateIsNotNull(PriceGroup priceGroup);

    List<Room> findByPriceGroup(PriceGroup priceGroup);

    boolean existsByIdAndIsAvailableIsTrue(int id);

    @Modifying
    @Query("UPDATE Room c SET c.isAvailable = false WHERE c.id = :id")
    void setIsAvailableToFalse(@Param("id") int id);

    //set room isavailable to true
    @Modifying
    @Query("UPDATE Room c SET c.isAvailable = true WHERE c.id = :id")
    void setIsAvailableToTrue(@Param("id") int id);

}
