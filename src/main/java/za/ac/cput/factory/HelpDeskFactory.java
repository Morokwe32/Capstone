package za.ac.cput.factory;

/**HelpDeskFactory.java
 * Factory class for Help Desk page
 * Author: Moegamat Behardien (219233829)
 * Date: 01/03/2024
 * */

import org.springframework.stereotype.Component;
import za.ac.cput.domain.HelpDesk;

import java.time.LocalDateTime;



@Component
public class HelpDeskFactory implements IFactory<HelpDesk> {
    public static HelpDesk helpDeskCreated(int id, String category, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new HelpDesk.Builder()
                .setId(id)
                .setCategory(category)
                .setTitle(title)
                .setContent(content)
                .setCreatedAt(createdAt)
                .setUpdatedAt(updatedAt)
                .build();
    }

    public static HelpDesk createDeskCenter(String category, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new HelpDesk.Builder()
                .setCategory(category)
                .setTitle(title)
                .setContent(content)
                .setCreatedAt(createdAt)
                .setUpdatedAt(updatedAt)
                .build();
    }

    @Override
    public HelpDesk create() {
        return HelpDesk.builder().build();
    }

    public HelpDesk create(HelpDesk helpDesk) {
        return HelpDesk.builder().copy(helpDesk).build();
    }
}

