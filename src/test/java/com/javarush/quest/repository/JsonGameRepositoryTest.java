package com.javarush.quest.repository;

import com.javarush.quest.config.Config;
import com.javarush.quest.entity.Answer;
import com.javarush.quest.entity.Question;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonGameRepositoryTest {
    private static GameRepository repository;

    @BeforeAll
    static void setUp() {
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

    @Test
    void shouldReadAnswersCorrectly() {
        final Question question = repository.read(Config.resource.defaultGameType(), 1);
        final Answer a = question.getAnswers()[0];

        assertNotNull(a.getText());
        assertTrue(a.getNextQuestionId() > 0);
    }

    @Test
    void shouldReadEndingQuestion() {
        final Question question = repository.read(Config.resource.defaultGameType(), 6);

        assertNotNull(question);
        assertNotNull(question.getEndingType());
    }

    @Test
    void shouldThrowExceptionWhenQuestionNotFound() {
        assertThrows(Exception.class, () ->
                repository.read(Config.resource.defaultGameType(), 999)
        );
    }

    @AfterAll
    static void shutdown() {
        repository = null;
    }
}
