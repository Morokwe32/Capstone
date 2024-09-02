package za.ac.cput.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.User;
import za.ac.cput.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3315")
@RestController
@RequestMapping("api/admin/users")
public class AdminUserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/list/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.read(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("userName") String userName,
            @RequestParam(value = "picture", required = false) MultipartFile picture) {

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setUserName(userName);

        // Handle file if it's not null
        if (picture != null && !picture.isEmpty()) {
            try {
                String fileName = picture.getOriginalFilename();
                byte[] fileBytes = picture.getBytes();
                // Save or process the file here
                // Assuming you save the file to a directory and get its URL
                String fileUrl = "/path/to/saved/file/" + fileName;
                newUser.setPictureUrl(fileUrl);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).build(); // Internal Server Error
            }
        } else {
            // If no picture is uploaded, ensure pictureUrl remains NULL
            newUser.setPictureUrl(null);
        }

        User createdUser = userService.create(newUser);
        return ResponseEntity.ok(createdUser);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        User existingUser = userService.read(id);

        if (existingUser != null) {
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());

            existingUser = userService.update(existingUser);
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        boolean deleted = userService.delete(userId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
