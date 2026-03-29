package com.javarush.quest.controller;

import com.javarush.quest.config.Config;
import com.javarush.quest.entity.Answer;
import com.javarush.quest.entity.Game;
import com.javarush.quest.entity.Question;
import com.javarush.quest.repository.GameRepository;
import com.javarush.quest.util.Constants;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestServletTest {
    @InjectMocks
    private QuestServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @BeforeAll
    static void setUp() {
        Config.load();
    }

    @Test
    void shouldStartNewGame() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(Constants.GAME_UUID)).thenReturn(null);
        when(request.getParameter(Constants.USERNAME)).thenReturn("username");
        when(request.getParameter(Constants.GAME_TYPE)).thenReturn(null);
        when(request.getRequestDispatcher("/game.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute(eq(Constants.USERNAME), anyString());
        verify(dispatcher).forward(request, response);
    }

    @Test
    void shouldProcessAnswer() throws Exception {
        final UUID gameId = UUID.randomUUID();
        final Question question = getTestQuestion();
        final Game game = new Game(gameId, Config.resource.defaultGameType(), question);

        servlet.getGameService().launch(game);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(Constants.GAME_UUID)).thenReturn(gameId);
        when(request.getParameter("answer")).thenReturn("10");
        when(request.getRequestDispatcher("/game.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(dispatcher).forward(request, response);
    }

    @Test
    void shouldGoToNextQuestion() {
        final GameRepository repo = servlet.getGameRepository();
        final Question question1 = repo.read(Config.resource.defaultGameType(), 1);

        final Answer a = question1.getAnswers()[0];
        final Question question2 = repo.read(Config.resource.defaultGameType(), a.getNextQuestionId());

        assertNotEquals(question1.getId(), question2.getId());
        assertNotEquals(question1.getText(), question2.getText());
    }

    Question getTestQuestion() {
        return new Question(
                1,
                "test question",
                new Answer[] {new Answer(10, "test answer", 2)},
                null
        );
    }
}