package za.ac.cput.factory;

/**FaqsFactory.java
 * Factory class for Faqs
 * Author: Tsireledzo Nemushungwa (221196803)
 * Date: 03/03/2024
 */

import org.springframework.stereotype.Component;
import za.ac.cput.domain.Faqs;

import java.time.LocalDateTime;


@Component
public class FaqsFactory implements IFactory<Faqs> {
    public static Faqs faqCreated(int id, String question, String answer, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Faqs.Builder()
                .setId(id)
                .setQuestion(question)
                .setAnswer(answer)
                .setCreatedAt(createdAt)
                .setUpdatedAt(updatedAt)
                .build();
    }

    public static Faqs createFaq(String question, String answer, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Faqs.Builder()
                .setQuestion(question)
                .setAnswer(answer)
                .setCreatedAt(createdAt)
                .setUpdatedAt(updatedAt)
                .build();
    }

    @Override
    public Faqs create() {
        return Faqs.builder().build();
    }

    public Faqs create(Faqs faq) {
        return Faqs.builder().copy(faq).build();
    }
}

