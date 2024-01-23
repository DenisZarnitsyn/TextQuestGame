package game;

import loading.QuestionLoader;
import lombok.Getter;
import lombok.Setter;
import models.Option;
import models.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
public class GameProcessor {
    private final Logger logger = LoggerFactory.getLogger(GameProcessor.class);

    private List<Question> questions;
    private int currentQuestionIndex;
    private String playerName;
    private HttpServletRequest request;
    private String gameResult;
    private String winnerName;
    private final QuestionLoader questionLoader;

    public GameProcessor(QuestionLoader questionLoader) {
        this.questionLoader = questionLoader;
        try {
            initializeGameProcessor();
        } catch (IOException e) {
            logger.error("Failed to initialize GameProcessor", e);
        }
    }

    protected void initializeGameProcessor() throws IOException {
        try {
            List<Question> loadedQuestions = questionLoader.loadQuestions();

            if (loadedQuestions != null && !loadedQuestions.isEmpty()) {
                this.questions = loadedQuestions;
                this.currentQuestionIndex = 0;
                logger.info("GameProcessor initialized successfully.");
            } else {
                logger.error("Failed to load questions. The game cannot be initialized. No questions loaded.");
                throw new IOException("No questions loaded.");
            }
        } catch (IOException e) {
            logger.error("Failed to load questions. The game cannot be initialized.", e);
        }
    }

    public Question getCurrentQuestion() {
        if (isInvalidState()) {
            return null;
        }

        return questions.get(currentQuestionIndex);
    }

    public void processAnswer(int optionIndex) {
        if (isInvalidState() || isInvalidOptionIndex(optionIndex)) {
            return;
        }

        Option selectedOption = getCurrentQuestion().getOptions().get(optionIndex - 1);

        if (selectedOption.getNextQuestionId() == -1) {
            handleGameOver();
        } else if (selectedOption.getNextQuestionId() == 0) {
            handleGameWin();
        } else {
            moveToNextQuestion(selectedOption.getNextQuestionId());
        }
    }

    private boolean isInvalidState() {
        return questions == null || questions.isEmpty() || currentQuestionIndex < 0 || currentQuestionIndex >= questions.size();
    }

    private boolean isInvalidOptionIndex(int optionIndex) {
        if (optionIndex < 1 || optionIndex > getCurrentQuestion().getOptions().size()) {
            logger.warn("Invalid option index: {}", optionIndex);
            return true;
        }
        return false;
    }

    public boolean isGameOver() {
        return questions == null || questions.isEmpty() || currentQuestionIndex >= questions.size();
    }

    private void moveToNextQuestion(int nextQuestionId) {
        questions.stream()
                .filter(question -> question.getId() == nextQuestionId)
                .findFirst()
                .ifPresent(question -> currentQuestionIndex = questions.indexOf(question));
    }

    private void handleGameOver() {
        endGame(false);
        gameResult = "lost";
        winnerName = playerName;
    }

    private void handleGameWin() {
        endGame(true);
        gameResult = "won";
        winnerName = playerName;
    }

    private void endGame(boolean isGameWon) {
        questions.clear();
        currentQuestionIndex = 0;

        if (isGameWon) {
            logger.info("Congratulations! You won the game.");
        } else {
            logger.info("Game over. You lost.");
        }
    }
}
