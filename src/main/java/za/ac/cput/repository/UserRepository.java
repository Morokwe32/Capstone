package za.ac.cput.repository;

/**
 * UserRepo.java
 *Repo of User Class
 *Author:Moegamat Isgak Abzal
 *Student Number: 221321810
 * */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}