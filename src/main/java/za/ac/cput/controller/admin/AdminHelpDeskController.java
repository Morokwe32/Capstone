package za.ac.cput.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.HelpDesk;
import za.ac.cput.service.impl.IHelpDeskServiceImpl;

import java.util.ArrayList;



@RestController
@RequestMapping("/api/admin/help-desk")
public class AdminHelpDeskController {
    @Autowired
    private IHelpDeskServiceImpl helpDeskService;

    @PostMapping("/create")
    public HelpDesk createHelpDesk(@RequestBody HelpDesk helpDesk) {
        return helpDeskService.create(helpDesk);
    }

    @GetMapping("/read/{id}")
    public HelpDesk readHelpDesk(@PathVariable int id) {
        HelpDesk helpDesk = helpDeskService.read(id);
        return helpDesk;
    }

    @PostMapping("/update")
    public HelpDesk updateHelpDesk(@RequestBody HelpDesk helpDesk) {
        return helpDeskService.update(helpDesk);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return helpDeskService.delete(id);
    }

    @GetMapping("/get-all")
    public ArrayList<HelpDesk> getAll() {
        ArrayList<HelpDesk> helpDeskList = new ArrayList<>(helpDeskService.getAll());
        return helpDeskList;
    }
}
