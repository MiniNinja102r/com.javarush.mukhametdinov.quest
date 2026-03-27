package com.javarush.quest.controller;

import java.io.*;

import com.javarush.quest.config.Config;
import com.javarush.quest.entity.Game;
import com.javarush.quest.entity.GameType;
import com.javarush.quest.repository.GameFactory;
import com.javarush.quest.repository.GameRepository;
import com.javarush.quest.repository.JsonGameRepository;
import com.javarush.quest.service.GameService;
import com.javarush.quest.service.JspGameService;
import com.javarush.quest.util.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "questPage", value = "/quest-page")
public final class QuestServlet extends HttpServlet {
    private GameService gameService = new JspGameService();
    private GameRepository gameRepository = new JsonGameRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("username");
        GameType type;
        try {
            String raw = request.getParameter("game");
            type = raw != null ? GameType.valueOf(raw.toUpperCase()) : Constants.DEFAULT_GAME_TYPE;
        } catch (Exception e) {
            type = Constants.DEFAULT_GAME_TYPE;
        }

        Config.load();
        final Game game = GameFactory.createGame(type);
        gameService.launch(game);

        gameRepository.read(type, 1);

        final HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("game", game);

        response.setContentType("text/html");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("OK");
        response.getWriter().println(session.getAttribute("username"));
        response.getWriter().println(session.getAttribute("game"));
    }
}