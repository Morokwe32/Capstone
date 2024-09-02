package za.ac.cput.factory;

/**
 * UserFactory.java
 *UserFactory of User Class
 *Author:Moegamat Isgak Abzal
 *Student Number: 221321810
 * */

import org.springframework.stereotype.Component;
import za.ac.cput.domain.User;

@Component
public class UserFactory implements IFactory<User>
{
    public User create(int id, String userName, String email, String profilePicture, String firstName, String lastName, String phoneNumber, String password)
    {
        return User.builder()
                .id(id)
                .userName(userName)
                .email(email)
                .pictureUrl(profilePicture)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .password(password)
                .build();
    }

    @Override
    public User create()
    {
        return User.builder().build();
    }

    public User create(User user)
    {
        return User.builder()
                .copy(user)
                .build();
    }
}
