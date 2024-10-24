package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
}
