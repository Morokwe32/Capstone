package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.AboutUs;

@Repository
public interface AboutUsRepository extends JpaRepository<AboutUs, Integer> {
}
