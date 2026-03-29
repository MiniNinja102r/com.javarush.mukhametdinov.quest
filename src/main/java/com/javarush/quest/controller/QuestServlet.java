package com.javarush.quest.controller;

import java.io.*;
import java.util.UUID;

import com.javarush.quest.config.Config;
import com.javarush.quest.entity.*;
import com.javarush.quest.exception.GameNotFoundException;
import com.javarush.quest.repository.GameFactory;
import com.javarush.quest.repository.GameRepository;
import com.javarush.quest.repository.JsonGameRepository;
import com.javarush.quest.service.GameService;
import com.javarush.quest.service.JspGameService;
import com.javarush.quest.util.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@WebServlet(name = "questPage", value = "/quest-page")
public final class QuestServlet extends HttpServlet {
    GameService gameService = new JspGameService();
    GameRepository gameRepository = new JsonGameRepository();

    @Override
    public void init() {
        Config.load();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();

        Game game;
        if (session.getAttribute(Constants.GAME_UUID) == null)
            game = this.startNewGame(request, session);
        else
            game = this.processResponse(request, session);

        request.setAttribute(Constants.QUESTION, game.getCurrentQuestion());
        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }

    private Game startNewGame(HttpServletRequest request, HttpSession session) {
        final String username = request.getParameter(Constants.USERNAME);
        session.setAttribute(Constants.USERNAME, username);

        Integer gamesCount = (Integer) session.getAttribute(Constants.GAMES_COUNT);
        session.setAttribute(Constants.GAMES_COUNT, gamesCount == null ? 1 : gamesCount + 1);

        String raw = request.getParameter(Constants.GAME_TYPE);
        final GameType type = raw == null
                ? Constants.DEFAULT_GAME_TYPE
                : GameType.valueOf(raw.toUpperCase());
        final Question current = gameRepository.read(type, Constants.FIRST_QUESTION_ID);

        final Game game = GameFactory.createGame(type, current);
        session.setAttribute(Constants.GAME_UUID, game.getId());

        gameService.launch(game);
        return game;
    }

    private Game processResponse(HttpServletRequest request, HttpSession session) {
        final Game game = gameService.getById((UUID) session.getAttribute(Constants.GAME_UUID));
        if (game == null)
            throw new GameNotFoundException("Game not found by id: " + session.getAttribute(Constants.GAME_UUID));

        final long currentId = game.getCurrentQuestion().getId();
        Question current = gameRepository.read(game.getGameType(), currentId);
        String answerId = request.getParameter("answer");

        for (Answer a : current.getAnswers()) {
            if (String.valueOf(a.getId()).equals(answerId)) {
                long nextId = a.getNextQuestionId();
                current = gameRepository.read(game.getGameType(), nextId);
                if (current.getEndingType() != null)
                    this.processFinish(game, current, session);

                game.setCurrentQuestion(current);
                break;
            }
        }
        return game;
    }

    private void processFinish(Game game, Question current, HttpSession session) {
        try {
            if (current.getEndingType() == EndingType.BAD) {
                System.out.println("LOOSE");
            } else {
                System.out.println("WIN");
            }
        } finally {
            session.setAttribute(Constants.GAME_UUID, null);
            gameService.remove(game.getId());
        }
    }
}
