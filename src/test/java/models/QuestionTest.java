package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
    }

    @Test
    void testDefaultValues() {
        assertEquals(0, question.getId(), "Id should be 0 by default");
        assertNull(question.getQuestion(), "Question should be null by default");
        assertNotNull(question.getOptions(), "Options should not be null by default");
        assertTrue(question.getOptions().isEmpty(), "Options should be empty by default");
    }

    @Test
    void testSetId() {
        question.setId(1);
        assertEquals(1, question.getId(), "Id should be set correctly");
    }

    @Test
    void testSetQuestion() {
        question.setQuestion("Sample Question");
        assertEquals("Sample Question", question.getQuestion(), "Question should be set correctly");
    }

    @Test
    void testSetOptions() {
        List<Option> options = Arrays.asList(new Option(), new Option());
        question.setOptions(options);
        assertEquals(options, question.getOptions(), "Options should be set correctly");
    }

    @Test
    void testEqualsAndHashCode() {
        Question question1 = new Question();
        Question question2 = new Question();

        assertEquals(question1, question2, "Questions should be equal");
        assertEquals(question1.hashCode(), question2.hashCode(), "Hash codes should be equal");

        question1.setId(1);
        assertNotEquals(question1, question2, "Questions should not be equal after setting Id");
        assertNotEquals(question1.hashCode(), question2.hashCode(), "Hash codes should not be equal after setting Id");

        question2.setId(1);
        assertEquals(question1, question2, "Questions should be equal after setting Id");
        assertEquals(question1.hashCode(), question2.hashCode(), "Hash codes should be equal after setting Id");

        question1.setQuestion("Question");
        assertNotEquals(question1, question2, "Questions should not be equal after setting Question");
        assertNotEquals(question1.hashCode(), question2.hashCode(), "Hash codes should not be equal after setting Question");

        question2.setQuestion("Question");
        assertEquals(question1, question2, "Questions should be equal after setting Question");
        assertEquals(question1.hashCode(), question2.hashCode(), "Hash codes should be equal after setting Question");

        List<Option> options = Arrays.asList(new Option(), new Option());
        question1.setOptions(options);
        assertNotEquals(question1, question2, "Questions should not be equal after setting Options");
        assertNotEquals(question1.hashCode(), question2.hashCode(), "Hash codes should not be equal after setting Options");

        question2.setOptions(options);
        assertEquals(question1, question2, "Questions should be equal after setting Options");
        assertEquals(question1.hashCode(), question2.hashCode(), "Hash codes should be equal after setting Options");
    }
}
