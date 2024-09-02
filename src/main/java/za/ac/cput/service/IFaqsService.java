package za.ac.cput.service;

/**IFaqsService.java
 * IService class for Faqs
 * Author: Tsireledzo Nemushungwa (221196803)
 * Date: 03/03/2024
 */

import za.ac.cput.domain.Faqs;

import java.util.ArrayList;

public interface IFaqsService extends IService<Faqs, Integer> {

    ArrayList<Faqs> getAll();
}
