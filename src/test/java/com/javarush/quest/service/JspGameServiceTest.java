package com.javarush.quest.service;

import com.javarush.quest.config.Config;
import com.javarush.quest.entity.Answer;
import com.javarush.quest.entity.Game;
import com.javarush.quest.entity.Question;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JspGameServiceTest {
    private static GameService service;

    @BeforeAll
    static void setUp() {
        Config.load();
        service = new JspGameService();
    }

    @Test
    void shouldStoreGame() {
        final Game game = new Game(UUID.randomUUID(), Config.resource.defaultGameType(), getTestQuestion());

        service.launch(game);
        Game found = service.getById(game.getId());

        assertNotNull(found);
        assertEquals(game.getId(), found.getId());
    }

    @Test
    void shouldRemoveGame() {
        final Game game = new Game(UUID.randomUUID(), Config.resource.defaultGameType(), getTestQuestion());

        service.launch(game);
        service.remove(game.getId());

        assertNull(service.getById(game.getId()));
    }

    @Test
    void shouldReturnNullForUnknownGame() {
        assertNull(service.getById(UUID.randomUUID()));
    }

    Question getTestQuestion() {
        return new Question(
                1,
                "test question",
                new Answer[] {new Answer(10, "test answer", 2)},
                null
        );
    }

    @AfterAll
    static void shutdown() {
        service = null;
    }
}