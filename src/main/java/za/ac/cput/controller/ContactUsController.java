package za.ac.cput.controller;

/**ContactUsController.java
 * Controller class for about us page
 * Author: Elijah Gafane Morokwe (219070296)
 * Date: 01/06/2024
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ContactUs;
import za.ac.cput.factory.ContactUsFactory;
import za.ac.cput.service.impl.ContactUsServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/contactUs")
public class ContactUsController {

    @Autowired
    private ContactUsServiceImpl contactUsService;

    // Create a new ContactUs entry
    @PostMapping("/create")
    public ResponseEntity<ContactUs> create(@RequestBody ContactUs contactUs) {
        ContactUs newContactUs = ContactUsFactory.buildContactUs(
                contactUs.getId(),
                contactUs.getTitle(),
                contactUs.getFirstName(),
                contactUs.getLastName(),
                contactUs.getEmail(),
                contactUs.getSubject(),
                contactUs.getMessage()
        );
        ContactUs saved = this.contactUsService.create(newContactUs);
        return ResponseEntity.ok(saved);
    }

    // Read a ContactUs entry by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<ContactUs> read(@PathVariable int id) {
        ContactUs contactUs = this.contactUsService.read(id);
        if (contactUs != null) {
            return ResponseEntity.ok(contactUs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an existing ContactUs entry
    @PutMapping("/update")
    public ResponseEntity<ContactUs> update(@RequestBody ContactUs contactUs) {
        ContactUs updatedContactUs = this.contactUsService.update(contactUs);
        if (updatedContactUs != null) {
            return ResponseEntity.ok(updatedContactUs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a ContactUs entry by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        boolean deleted = this.contactUsService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok("ContactUs entry deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get a list of all ContactUs entries
    @GetMapping("/all")
    public ResponseEntity<List<ContactUs>> findAll() {
        List<ContactUs> contactUsList = this.contactUsService.findAll();
        return ResponseEntity.ok(contactUsList);
    }
}
