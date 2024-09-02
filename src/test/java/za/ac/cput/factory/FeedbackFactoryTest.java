package za.ac.cput.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeedbackFactoryTest {

    @Test
    public void testCreateFeedback() {
        FeedbackFactory feedbackFactory = new FeedbackFactory();
        Feedback feedback = feedbackFactory.create();
        Assertions.assertNotNull(feedback);
        Assertions.assertNotNull(feedback.getId());
    }

}
