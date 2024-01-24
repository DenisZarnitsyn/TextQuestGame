package game;

import loading.QuestionLoader;
import models.Option;
import models.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GameProcessorTest {
    private GameProcessor gameProcessor;

    @BeforeEach
    void setUp() {
        QuestionLoader questionLoader = new QuestionLoader();
        gameProcessor = new GameProcessor(questionLoader);

        List<Question> mockQuestions = Arrays.asList(
                createMockQuestion(1, "Question 1", createMockOptions(2, 3)),
                createMockQuestion(2, "Question 2", createMockOptions(4, -1)),
                createMockQuestion(3, "Question 3", createMockOptions(0, -1))
        );

        gameProcessor.setQuestions(mockQuestions);
    }

    private Question createMockQuestion(int id, String text, List<Option> options) {
        Question question = new Question();
        question.setId(id);
        question.setQuestion(text);
        question.setOptions(options);
        return question;
    }

    private List<Option> createMockOptions(int... nextQuestionIds) {
        List<Option> options = Arrays.asList(new Option(), new Option());
        for (int i = 0; i < options.size(); i++) {
            options.get(i).setNextQuestionId(nextQuestionIds[i]);
        }
        return options;
    }

    @Test
    void testGetCurrentQuestion() {
        assertEquals("Question 1", gameProcessor.getCurrentQuestion().getQuestion());
    }

    @Test
    void testProcessAnswer() {
        QuestionLoader questionLoader = new QuestionLoader();
        GameProcessor gameProcessor = new GameProcessor(questionLoader);

        gameProcessor.setCurrentQuestionIndex(0);
        gameProcessor.setPlayerName("PlayerName");

        gameProcessor.processAnswer(1);

        assertNotNull(gameProcessor.getCurrentQuestion(), "Current question should not be null");
    }

    @Test
    void testEndGame() {
        gameProcessor.setPlayerName("PlayerName");
        gameProcessor.endGame(true);
        assertTrue(gameProcessor.isGameOver());
        assertEquals("won", gameProcessor.getGameResult());
        assertEquals("PlayerName", gameProcessor.getWinnerName());
    }

    @Test
    void testHandleGameWin() {
        gameProcessor.setPlayerName("PlayerName");
        gameProcessor.handleGameWin();
        assertTrue(gameProcessor.isGameOver());
        assertEquals("won", gameProcessor.getGameResult());
        assertEquals("PlayerName", gameProcessor.getWinnerName());
    }

    @Test
    void testSetRequest() {
        HttpServletRequest mockRequest = new MockHttpServletRequest();
        gameProcessor.setRequest(mockRequest);
        assertEquals(mockRequest, gameProcessor.getRequest());
    }

    private static class MockHttpServletRequest implements HttpServletRequest {

        @Override
        public String getAuthType() {
            return null;
        }

        @Override
        public Cookie[] getCookies() {
            return new Cookie[0];
        }

        @Override
        public long getDateHeader(String s) {
            return 0;
        }

        @Override
        public String getHeader(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaders(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return null;
        }

        @Override
        public int getIntHeader(String s) {
            return 0;
        }

        @Override
        public String getMethod() {
            return null;
        }

        @Override
        public String getPathInfo() {
            return null;
        }

        @Override
        public String getPathTranslated() {
            return null;
        }

        @Override
        public String getContextPath() {
            return null;
        }

        @Override
        public String getQueryString() {
            return null;
        }

        @Override
        public String getRemoteUser() {
            return null;
        }

        @Override
        public boolean isUserInRole(String s) {
            return false;
        }

        @Override
        public Principal getUserPrincipal() {
            return null;
        }

        @Override
        public String getRequestedSessionId() {
            return null;
        }

        @Override
        public String getRequestURI() {
            return null;
        }

        @Override
        public StringBuffer getRequestURL() {
            return null;
        }

        @Override
        public String getServletPath() {
            return null;
        }

        @Override
        public HttpSession getSession(boolean b) {
            return null;
        }

        @Override
        public HttpSession getSession() {
            return null;
        }

        @Override
        public String changeSessionId() {
            return null;
        }

        @Override
        public boolean isRequestedSessionIdValid() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromCookie() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromURL() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromUrl() {
            return false;
        }

        @Override
        public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
            return false;
        }

        @Override
        public void login(String s, String s1) throws ServletException {

        }

        @Override
        public void logout() throws ServletException {

        }

        @Override
        public Collection<Part> getParts() throws IOException, ServletException {
            return null;
        }

        @Override
        public Part getPart(String s) throws IOException, ServletException {
            return null;
        }

        @Override
        public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
            return null;
        }

        @Override
        public Object getAttribute(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String getCharacterEncoding() {
            return null;
        }

        @Override
        public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

        }

        @Override
        public int getContentLength() {
            return 0;
        }

        @Override
        public long getContentLengthLong() {
            return 0;
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public String getParameter(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return null;
        }

        @Override
        public String[] getParameterValues(String s) {
            return new String[0];
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return null;
        }

        @Override
        public String getProtocol() {
            return null;
        }

        @Override
        public String getScheme() {
            return null;
        }

        @Override
        public String getServerName() {
            return null;
        }

        @Override
        public int getServerPort() {
            return 0;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return null;
        }

        @Override
        public String getRemoteAddr() {
            return null;
        }

        @Override
        public String getRemoteHost() {
            return null;
        }

        @Override
        public void setAttribute(String s, Object o) {

        }

        @Override
        public void removeAttribute(String s) {

        }

        @Override
        public Locale getLocale() {
            return null;
        }

        @Override
        public Enumeration<Locale> getLocales() {
            return null;
        }

        @Override
        public boolean isSecure() {
            return false;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String s) {
            return null;
        }

        @Override
        public String getRealPath(String s) {
            return null;
        }

        @Override
        public int getRemotePort() {
            return 0;
        }

        @Override
        public String getLocalName() {
            return null;
        }

        @Override
        public String getLocalAddr() {
            return null;
        }

        @Override
        public int getLocalPort() {
            return 0;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public AsyncContext startAsync() throws IllegalStateException {
            return null;
        }

        @Override
        public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
            return null;
        }

        @Override
        public boolean isAsyncStarted() {
            return false;
        }

        @Override
        public boolean isAsyncSupported() {
            return false;
        }

        @Override
        public AsyncContext getAsyncContext() {
            return null;
        }

        @Override
        public DispatcherType getDispatcherType() {
            return null;
        }
    }
}
