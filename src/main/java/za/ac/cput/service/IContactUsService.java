package za.ac.cput.service;

/**IContactUsService.java
 * IService class for about us page
 * Author: Elijah Gafane Morokwe (219070296)
 * Date: 01/03/2024
 */

import za.ac.cput.domain.ContactUs;

import java.util.ArrayList;

public interface IContactUsService {

    ContactUs create(ContactUs contactUs);

    ContactUs read(int id);

    ContactUs update(ContactUs contactUs);

    boolean deleteById(int id);

    ArrayList<ContactUs> findAll();
}
