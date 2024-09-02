package za.ac.cput.service.impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.HelpDesk;
import za.ac.cput.repository.IHelpDeskRepository;
import za.ac.cput.service.IHelpDeskService;

/**HelpDeskServiceImpl.java
 * ServiceImpl class for Help Desk page
 * Author: Moegamat Behardien (219233829)
 * Date: 01/03/2024
 * */

import java.util.ArrayList;
import java.util.Optional;

@Service("iHelpDeskServiceImpl")
public class IHelpDeskServiceImpl implements IHelpDeskService {

    private static IHelpDeskRepository repository;

    public IHelpDeskServiceImpl(IHelpDeskRepository repository) {
        this.repository = repository;
    }

    @Override
    public HelpDesk create(HelpDesk helpDesk) {
//        HelpDesk helpDesk1 = helpDeskFactory.create(helpDesk);
//        return repository.save(helpDesk1);
        return this.repository.save(helpDesk);
    }

    @Override
    public HelpDesk read(Integer integer) {
        Optional<HelpDesk> helpDesk = this.repository.findById(integer);
        return helpDesk.orElse(null);
//        return this.repository.findById(id).orElse(null);
    }

    @Override
    public HelpDesk update(HelpDesk helpDesk) {
//        if (this.repository.existsById((int) helpDesk.getId())) {
//            HelpDesk helpDesk1 = helpDeskFactory.create(helpDesk);
//            return this.repository.save(helpDesk1);
//        }
//        return null;
        if (this.repository.existsById(helpDesk.getId())) {
            return this.repository.save(helpDesk);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<HelpDesk> getAll() {
        ArrayList<HelpDesk> allHelpDesk = (ArrayList<HelpDesk>) this.repository.findAll();
        return allHelpDesk;
    }

    public ArrayList<HelpDesk> getAllByCategory(String category) {
        return this.repository.findAllByCategory(category);
    }
}
