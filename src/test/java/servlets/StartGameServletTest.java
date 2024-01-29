package servlets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

class StartGameServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private StartGameServlet startGameServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDoGet() throws ServletException, IOException {
        when(request.getRequestDispatcher("/WEB-INF/views/start.jsp")).thenReturn(requestDispatcher);

        startGameServlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPost() throws ServletException, IOException {
        when(request.getParameter("userName")).thenReturn("John");

        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        startGameServlet.doPost(request, response);

        verify(request).getSession();

        verify(session).setAttribute(eq("gameProcessor"), any());
        verify(session).setAttribute(eq("questions"), any());

        verify(response).sendRedirect(request.getContextPath() + "/game");
    }
}
