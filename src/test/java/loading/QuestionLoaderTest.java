package loading;

import models.Question;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionLoaderTest {

    @Test
    void testLoadQuestions() {
        QuestionLoader questionLoader = new QuestionLoader();
        List<Question> questions = questionLoader.loadQuestions();

        assertNotNull(questions);
        assertFalse(questions.isEmpty());

        for (Question question : questions) {
            assertNotNull(question.getId());
            assertNotNull(question.getQuestion());
            assertNotNull(question.getOptions());
            assertFalse(question.getOptions().isEmpty());

            for (int i = 0; i < question.getOptions().size(); i++) {
                assertNotNull(question.getOptions().get(i).getText());
                assertNotNull(question.getOptions().get(i).getNextQuestionId());
            }
        }
    }

    @Test
    void testJsonLoadException() {
        QuestionLoader questionLoader = new QuestionLoader();
        assertThrows(QuestionLoader.JsonLoadException.class, () -> {
            throw questionLoader.new JsonLoadException("test message", new RuntimeException());
        });
    }
}
