package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.AboutUs;
import za.ac.cput.service.impl.AboutUsServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/aboutUs")
public class AboutUsController {

    @Autowired
    private AboutUsServiceImpl service;

    // Create a new AboutUs entry
    @PostMapping("/create")
    public ResponseEntity<AboutUs> create(@RequestBody AboutUs aboutUs) {
        AboutUs newAboutUs = this.service.create(aboutUs);
        return new ResponseEntity<>(newAboutUs, HttpStatus.CREATED);
    }

    // Read an AboutUs entry by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<AboutUs> read(@PathVariable("id") int id) {
        AboutUs readAbout = this.service.read(id);
        if (readAbout != null) {
            return ResponseEntity.ok(readAbout);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing AboutUs entry
    @PutMapping("/update")
    public ResponseEntity<AboutUs> update(@RequestBody AboutUs aboutUs) {
        AboutUs updatedAboutUs = this.service.update(aboutUs);
        if (updatedAboutUs != null) {
            return ResponseEntity.ok(updatedAboutUs);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete an AboutUs entry by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean deleted = this.service.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get a list of all AboutUs entries
    @GetMapping("/list")
    public ResponseEntity<List<AboutUs>> getAll() {
        List<AboutUs> allAboutUs = this.service.getAll();
        return ResponseEntity.ok(allAboutUs);
    }
}
