package za.ac.cput.repository;

/**IFaqsRepository.java
 * IRepository class for Faqs
 * Author: Tsireledzo Nemushungwa (221196803)
 * Date: 03/03/2024
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Faqs;

@Repository
public interface IFaqsRepository extends JpaRepository<Faqs, Integer> {
}
