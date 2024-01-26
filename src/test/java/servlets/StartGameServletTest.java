package servlets;

import game.GameProcessor;
import loading.QuestionLoader;
import models.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class StartGameServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private HttpSession session;

    @Mock
    private QuestionLoader questionLoader;

    @Mock
    private GameProcessor gameProcessor;

    @InjectMocks
    private StartGameServlet servlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/views/start.jsp")).thenReturn(dispatcher);
    }

    @Test
    void testDoPost() throws Exception {
        List<Question> questions = new ArrayList<>();
        when(questionLoader.loadQuestions()).thenReturn(questions);

        when(request.getParameter("userName")).thenReturn("John");

        servlet.doPost(request, response);

        verify(gameProcessor).setPlayerName("John");
        verify(gameProcessor).setRequest(request);
        verify(request.getSession()).setAttribute("gameProcessor", gameProcessor);
        verify(request.getSession()).setAttribute("questions", questions);
        verify(response).sendRedirect(request.getContextPath() + "/game");
    }
}
