package loading;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class QuestionLoader {
    private final Logger logger = LoggerFactory.getLogger(QuestionLoader.class);
    private final String JSON_FILE_PATH = "/questions.json";

    public List<Question> loadQuestions() {
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = Optional.ofNullable(getClass().getResourceAsStream(JSON_FILE_PATH))
                .orElseThrow(() -> new JsonLoadException("Unable to find " + JSON_FILE_PATH + " file", new IOException("Resource not found")))) {

            List<Question> questions = objectMapper.readValue(inputStream, new TypeReference<List<Question>>() {
            });
            logger.info("Successfully loaded the questions.json file.");

            return questions;
        } catch (IOException e) {
            logger.error("Error loading questions: {}", e.getMessage(), e);
            throw new JsonLoadException("Error loading questions", e);
        }
    }

    public class JsonLoadException extends RuntimeException {
        public JsonLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}