package za.ac.cput.repository;

/**IHelpDeskRepository.java
 * Repository class for Help Desk page
 * Author: Moegamat Behardien (219233829)
 * Date: 01/03/2024
 * */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.HelpDesk;

import java.util.ArrayList;

@Repository
public interface IHelpDeskRepository extends JpaRepository<HelpDesk, Integer> {
    public ArrayList<HelpDesk> findAllByCategory(String category);
}
