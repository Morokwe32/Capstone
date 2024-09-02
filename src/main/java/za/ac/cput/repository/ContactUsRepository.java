package za.ac.cput.repository;

/**RepoFactory.java
 * Repo class for about us page
 * Author: Elijah Gafane Morokwe (219070296)
 * Date: 01/03/2024
 */

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.ContactUs;

import java.util.Optional;

public interface ContactUsRepository extends JpaRepository<ContactUs, Integer>
{
    Optional<ContactUs> findById(int id);

}
