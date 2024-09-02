package za.ac.cput.controller;

/**HelpDeskController.java
 * Controller class for Help Desk page
 * Author: Moegamat Behardien (219233829)
 * Date: 12/06/2024
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.HelpDesk;
import za.ac.cput.service.impl.IHelpDeskServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/help-desk")
@CrossOrigin(origins = "http://localhost:8080") // Adjust if necessary
public class HelpDeskController {

    @Autowired
    private IHelpDeskServiceImpl helpDeskService;

    // Get all help desk records
    @GetMapping("/get-all")
    public ResponseEntity<List<HelpDesk>> getAllHelpDesks() {
        List<HelpDesk> helpDeskList = helpDeskService.getAll();
        return ResponseEntity.ok(helpDeskList);
    }

    // Get help desk records by category
    @GetMapping("/get-all-by-category/{category}")
    public ResponseEntity<List<HelpDesk>> getAllByCategory(@PathVariable String category) {
        List<HelpDesk> helpDeskList = helpDeskService.getAllByCategory(category);
        return ResponseEntity.ok(helpDeskList);
    }
}
