package za.ac.cput.service.impl;

/**IFaqsServiceImpl.java
 * ServiceImpl class for Faqs
 * Author: Tsireledzo Nemushungwa (221196803)
 * Date: 03/03/2024
 */

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Faqs;
import za.ac.cput.repository.IFaqsRepository;
import za.ac.cput.service.IFaqsService;

import java.util.ArrayList;

@Service("iFaqsServiceImpl")
public class IFaqsServiceImpl implements IFaqsService {

    private static IFaqsRepository repository;

    public IFaqsServiceImpl(IFaqsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Faqs create(Faqs faq) {
        return this.repository.save(faq);
    }

    @Override
    public Faqs read(Integer integer) {
        return this.repository.findById(integer).orElse(null);
    }

    @Override
    public Faqs update(Faqs faq) {
        if (this.repository.existsById(faq.getId()))
            return this.repository.save(faq);
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        if (this.repository.existsById(integer)) {
            this.repository.deleteById(integer);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Faqs> getAll() {
        ArrayList<Faqs> allFaq = (ArrayList<Faqs>) this.repository.findAll();
        return allFaq;
    }
}
