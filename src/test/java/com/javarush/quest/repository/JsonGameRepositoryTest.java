package com.javarush.quest.repository;

import com.javarush.quest.config.Config;
import com.javarush.quest.entity.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonGameRepositoryTest {
    private GameRepository repository;

    @BeforeEach
    void setUp() {
        Config.load();
        repository = new JsonGameRepository();
    }

    @Test
    void shouldReadFirstQuestion() {
        final Question question = repository.read(Config.resource.defaultGameType(), 1);

        assertNotNull(question);
        assertEquals(1, question.getId());
        assertNotNull(question.getText());
        assertTrue(question.getAnswers().length > 0);
    }
}
