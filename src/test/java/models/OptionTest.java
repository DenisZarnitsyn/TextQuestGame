package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OptionTest {

    private Option option;

    @BeforeEach
    void setUp() {
        option = new Option();
    }

    @Test
    void testDefaultValues() {
        assertNotNull(option.getText(), "Text should not be null");
        assertTrue(option.getText().isEmpty(), "Text should be empty");
        assertEquals(0, option.getNextQuestionId(), "NextQuestionId should be 0");
    }

    @Test
    void testSetText() {
        option.setText("Sample Text");
        assertEquals("Sample Text", option.getText(), "Text should be set correctly");
    }

    @Test
    void testSetNextQuestionId() {
        option.setNextQuestionId(42);
        assertEquals(42, option.getNextQuestionId(), "NextQuestionId should be set correctly");
    }

    @Test
    void testEqualsAndHashCode() {
        Option option1 = new Option();
        Option option2 = new Option();

        assertEquals(option1, option2, "Options should be equal");
        assertEquals(option1.hashCode(), option2.hashCode(), "Hash codes should be equal");

        option1.setText("Text");
        assertNotEquals(option1, option2, "Options should not be equal after setting text");
        assertNotEquals(option1.hashCode(), option2.hashCode(), "Hash codes should not be equal after setting text");

        option2.setText("Text");
        assertEquals(option1, option2, "Options should be equal after setting text");
        assertEquals(option1.hashCode(), option2.hashCode(), "Hash codes should be equal after setting text");

        option1.setNextQuestionId(42);
        assertNotEquals(option1, option2, "Options should not be equal after setting NextQuestionId");
        assertNotEquals(option1.hashCode(), option2.hashCode(), "Hash codes should not be equal after setting NextQuestionId");

        option2.setNextQuestionId(42);
        assertEquals(option1, option2, "Options should be equal after setting NextQuestionId");
        assertEquals(option1.hashCode(), option2.hashCode(), "Hash codes should be equal after setting NextQuestionId");
    }
}
