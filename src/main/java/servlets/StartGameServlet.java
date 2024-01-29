package servlets;

import game.GameProcessor;
import loading.QuestionLoader;
import models.Question;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StartGameServlet", urlPatterns = {"/start-game"})
public class StartGameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToStartPage(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String playerName = request.getParameter("userName");

        if (playerName != null && !playerName.isEmpty()) {
            initializeGame(request, playerName);
            response.sendRedirect(request.getContextPath() + "/game");
        } else {
            forwardToStartPage(request, response);
        }
    }

    private void initializeGame(HttpServletRequest request, String playerName) {
        QuestionLoader questionLoader = new QuestionLoader();
        List<Question> questions = questionLoader.loadQuestions();

        GameProcessor gameProcessor = new GameProcessor(questionLoader);
        gameProcessor.setPlayerName(playerName);
        gameProcessor.setRequest(request);

        HttpSession session = request.getSession();
        session.setAttribute("gameProcessor", gameProcessor);
        session.setAttribute("questions", questions);
    }

    private void forwardToStartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/start.jsp");
        dispatcher.forward(request, response);
    }
}
