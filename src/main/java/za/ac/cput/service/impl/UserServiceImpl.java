package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.User;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.UserRepository;
import za.ac.cput.service.IUserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    private final UserRepository repository;
    private final UserFactory userFactory;

    @Autowired
    private PasswordEncoder passwordEncoder; // Autowire PasswordEncoder

    @Autowired
    public UserServiceImpl(UserRepository repository, UserFactory userFactory) {
        this.repository = repository;
        this.userFactory = userFactory;
    }

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password before saving
        User newUser = userFactory.create(user);
        return repository.save(newUser);
    }

    @Override
    public User read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User update(User user) {
        if (repository.existsById(user.getId())) {
            User updatedUser = userFactory.create(user);
            return repository.save(updatedUser);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Method to retrieve User by email (used for login)
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    // Implement loadUserByUsername for security
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Looking for user with email: " + email);
        User foundUser = repository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("User not found with email: " + email);
                    return new UsernameNotFoundException("User not found with email: " + email);
                });

        return org.springframework.security.core.userdetails.User.builder()
                .username(foundUser.getEmail())
                .password(foundUser.getPassword())
                .roles("USER") // Default role for users
                .build();
    }
}
