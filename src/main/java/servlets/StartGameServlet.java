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
import java.io.IOException;
import java.util.List;


@WebServlet(name = "StartGameServlet", urlPatterns = {"/start-game"})
public class StartGameServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/start.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String playerName = request.getParameter("userName");

        QuestionLoader questionLoader = new QuestionLoader();
        List<Question> questions = questionLoader.loadQuestions();

        GameProcessor gameProcessor = new GameProcessor(questionLoader);
        gameProcessor.setPlayerName(playerName);
        gameProcessor.setRequest(request);

        request.getSession().setAttribute("gameProcessor", gameProcessor);
        request.getSession().setAttribute("questions", questions);

        response.sendRedirect(request.getContextPath() + "/game");
    }
}
