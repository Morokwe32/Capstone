package za.ac.cput.service;

/**IHelpDeskService.java
 * IService class for Help Desk page
 * Author: Moegamat Behardien (219233829)
 * Date: 01/03/2024
 * */

import za.ac.cput.domain.HelpDesk;

import java.util.ArrayList;

public interface IHelpDeskService extends IService<HelpDesk, Integer> {

    ArrayList<HelpDesk> getAll();
}