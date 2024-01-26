package servlets;

import game.GameProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GameServletTest {

    @Mock
    private GameProcessor gameProcessor;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private HttpSession session;

    @InjectMocks
    private GameServlet servlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testDoGetGameInProgress() throws Exception {
        when(session.getAttribute("gameProcessor")).thenReturn(gameProcessor);
        when(gameProcessor.isGameOver()).thenReturn(false);
        when(request.getRequestDispatcher("/WEB-INF/views/game.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("currentQuestion", gameProcessor.getCurrentQuestion());
        verify(dispatcher).forward(request, response);
    }


    @Test
    void testDoGetGameNotStarted() throws Exception {
        when(session.getAttribute("gameProcessor")).thenReturn(null);

        servlet.doGet(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/start-game");
    }

    @Test
    void testDoGetGameIsOver() throws Exception {
        when(session.getAttribute("gameProcessor")).thenReturn(gameProcessor);
        when(gameProcessor.isGameOver()).thenReturn(true);

        servlet.doGet(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/start-game");
    }

    @Test
    void testDoPostGameInProgress() throws Exception {
        when(session.getAttribute("gameProcessor")).thenReturn(gameProcessor);
        when(request.getParameter("selectedOption")).thenReturn("1");

        servlet.doPost(request, response);

        verify(gameProcessor).setRequest(request);
        verify(gameProcessor).processAnswer(1);
        verify(response).sendRedirect(request.getContextPath() + "/game");
    }

    @Test
    void testDoPostGameIsOver() throws Exception {
        when(session.getAttribute("gameProcessor")).thenReturn(gameProcessor);
        when(request.getParameter("selectedOption")).thenReturn("1");
        when(gameProcessor.isGameOver()).thenReturn(true);

        servlet.doPost(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/game-over");
    }

    @Test
    void testDoPostGameNotStarted() throws Exception {
        when(session.getAttribute("gameProcessor")).thenReturn(null);

        servlet.doPost(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/start-game");
    }
}
