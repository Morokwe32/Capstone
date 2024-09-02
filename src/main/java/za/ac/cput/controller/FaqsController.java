package za.ac.cput.controller;

/**FaqsController.java
 * Controller class for Faqs
 * Author: Tsireledzo Nemushungwa (221196803)
 * Date: 04/06/2024
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Faqs;
import za.ac.cput.service.IFaqsService;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")
@CrossOrigin(origins = "http://localhost:8080") // Adjust the port if necessary
public class FaqsController {

    @Autowired
    private IFaqsService faqsService;

    // Create a new FAQ
    @PostMapping("/create")
    public ResponseEntity<Faqs> create(@RequestBody Faqs faq) {
        Faqs createdFaq = faqsService.create(faq);
        return ResponseEntity.ok(createdFaq);
    }

    // Read an FAQ by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Faqs> read(@PathVariable Integer id) {
        Faqs faq = faqsService.read(id);
        return faq != null ? ResponseEntity.ok(faq) : ResponseEntity.notFound().build();
    }

    // Update an existing FAQ
    @PutMapping("/update")
    public ResponseEntity<Faqs> update(@RequestBody Faqs faq) {
        Faqs updatedFaq = faqsService.update(faq);
        return updatedFaq != null ? ResponseEntity.ok(updatedFaq) : ResponseEntity.notFound().build();
    }

    // Delete an FAQ by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        boolean deleted = faqsService.delete(id);
        return deleted ? ResponseEntity.ok("FAQ deleted successfully") : ResponseEntity.notFound().build();
    }

    // Get all FAQs
    @GetMapping("/all")
    public ResponseEntity<List<Faqs>> getAll() {
        List<Faqs> allFaq = faqsService.getAll();
        return ResponseEntity.ok(allFaq);
    }
}
