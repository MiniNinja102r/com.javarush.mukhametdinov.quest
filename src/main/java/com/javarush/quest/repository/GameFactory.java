package com.javarush.quest.repository;

import com.javarush.quest.config.Config;
import com.javarush.quest.entity.Game;
import com.javarush.quest.entity.GameType;
import com.javarush.quest.entity.Question;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@UtilityClass
public final class GameFactory {

    @NotNull
    public static Game createGame(@NotNull Question currentQuestion) {
        return createGame(Config.resource.defaultGameType(), currentQuestion);
    }

    @NotNull
    public static Game createGame(@NotNull GameType type, @NotNull Question currentQuestion) {
        return new Game(UUID.randomUUID(), type, currentQuestion);
    }
}
