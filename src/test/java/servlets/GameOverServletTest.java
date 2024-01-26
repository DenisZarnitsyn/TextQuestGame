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

import static org.mockito.Mockito.*;

class GameOverServletTest {

    @Mock
    private GameProcessor gameProcessor;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private GameOverServlet servlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDoGet() throws Exception {
        when(request.getSession()).thenReturn(mock(HttpSession.class)); // Додайте цей рядок

        when(request.getRequestDispatcher("/WEB-INF/views/game_over.jsp")).thenReturn(dispatcher);
        when(request.getSession().getAttribute("gameProcessor")).thenReturn(gameProcessor);
        when(gameProcessor.getGameResult()).thenReturn("Win");
        when(gameProcessor.getWinnerName()).thenReturn("John");

        servlet.doGet(request, response);

        verify(request).setAttribute("gameResult", "Win");
        verify(request).setAttribute("winnerName", "John");
        verify(dispatcher).forward(request, response);
    }

}
