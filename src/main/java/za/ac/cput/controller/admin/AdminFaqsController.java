package za.ac.cput.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Faqs;
import za.ac.cput.domain.Faqs;
import za.ac.cput.service.impl.IFaqsServiceImpl;
import za.ac.cput.service.impl.IFaqsServiceImpl;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/admin/faq")
public class AdminFaqsController {
    @Autowired
    private IFaqsServiceImpl faqsService;

    @PostMapping("/create")
    public Faqs createFaq(@RequestBody Faqs faqs) {
        return faqsService.create(faqs);
    }

    @GetMapping("/read/{id}")
    public Faqs readFaq(@PathVariable int id) {
        return faqsService.read(id);
    }

    @PostMapping("/update")
    public Faqs updateFaq(@RequestBody Faqs faqs) {
        return faqsService.update(faqs);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return faqsService.delete(id);
    }

    @GetMapping("/get-all")
    public ArrayList<Faqs> getAll() {
        ArrayList<Faqs> allFaq = new ArrayList<>(faqsService.getAll());
        return allFaq;
    }
}
