package game;

import loading.QuestionLoader;
import models.Option;
import models.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameProcessorTest {

    @Mock
    private QuestionLoader questionLoader;

    @InjectMocks
    private GameProcessor gameProcessor;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        when(questionLoader.loadQuestions()).thenReturn(questions);

        gameProcessor.initializeGameProcessor();
    }

    @AfterEach
    void tearDown() {}

    @Test
    void initializeGameProcessorSuccessfully() {
        assertAll("GameProcessor state after initialization",
                () -> assertNotNull(gameProcessor.getQuestions()),
                () -> assertEquals(0, gameProcessor.getCurrentQuestionIndex()),
                () -> assertEquals(1, gameProcessor.getQuestions().size()),
                () -> assertNull(gameProcessor.getGameResult()),
                () -> assertNull(gameProcessor.getWinnerName())
        );

        verify(questionLoader, times(1)).loadQuestions();
    }

    @Test
    void initializeGameProcessorWithIOException() {
        when(questionLoader.loadQuestions()).thenThrow(new IOException("Failed to load questions."));

        IOException exception = assertThrows(IOException.class, () -> gameProcessor.initializeGameProcessor());

        assertEquals("Failed to load questions.", exception.getMessage());

        assertNull(gameProcessor.getQuestions());
        assertEquals(0, gameProcessor.getCurrentQuestionIndex());
        assertNull(gameProcessor.getGameResult());
        assertNull(gameProcessor.getWinnerName());

        verify(questionLoader, times(1)).loadQuestions();
    }

    @Test
    void getCurrentQuestion() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        gameProcessor.setQuestions(questions);

        assertNotNull(gameProcessor.getCurrentQuestion());
    }

    @Test
    void processAnswerWithInvalidState() {
        gameProcessor.setQuestions(null);

        assertDoesNotThrow(() -> gameProcessor.processAnswer(1));
    }

    @Test
    void processAnswerWithInvalidOptionIndex() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        Option option = new Option();
        option.setText("Option 1");
        question.setOptions(List.of(option));
        questions.add(question);
        gameProcessor.setQuestions(questions);

        assertDoesNotThrow(() -> gameProcessor.processAnswer(0));
        assertDoesNotThrow(() -> gameProcessor.processAnswer(2));
    }

    @Test
    void processAnswerGameOver() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        Option option = new Option();
        option.setText("Option 1");
        option.setNextQuestionId(-1);
        question.setOptions(List.of(option));
        questions.add(question);
        gameProcessor.setQuestions(questions);

        gameProcessor.processAnswer(1);

        assertTrue(gameProcessor.isGameOver());
        assertEquals("lost", gameProcessor.getGameResult());
        assertEquals(gameProcessor.getPlayerName(), gameProcessor.getWinnerName());
    }

    @Test
    void processAnswerGameWin() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        Option option = new Option();
        option.setText("Option 1");
        option.setNextQuestionId(0);
        question.setOptions(List.of(option));
        questions.add(question);
        gameProcessor.setQuestions(questions);

        gameProcessor.processAnswer(1);

        assertTrue(gameProcessor.isGameOver());
        assertEquals("won", gameProcessor.getGameResult());
        assertEquals(gameProcessor.getPlayerName(), gameProcessor.getWinnerName());
    }

    @Test
    void processAnswerMoveToNextQuestion() {
        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        Option option1 = new Option();
        option1.setText("Option 1");
        option1.setNextQuestionId(2);
        question1.setOptions(List.of(option1));
        question1.setId(1);

        Question question2 = new Question();
        question2.setId(2);

        questions.add(question1);
        questions.add(question2);

        gameProcessor.setQuestions(questions);

        gameProcessor.processAnswer(1);

        assertEquals(1, gameProcessor.getCurrentQuestionIndex());
    }
}
