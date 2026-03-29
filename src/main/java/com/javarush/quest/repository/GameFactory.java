package com.javarush.quest.repository;

import com.javarush.quest.entity.Game;
import com.javarush.quest.entity.GameType;
import com.javarush.quest.entity.Question;
import com.javarush.quest.util.Constants;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@UtilityClass
public final class GameFactory {

    @NotNull
    public static Game createGame(@NotNull Question currentQuestion) {
        return createGame(Constants.DEFAULT_GAME_TYPE, currentQuestion);
    }

    @NotNull
    public static Game createGame(@NotNull GameType type, @NotNull Question currentQuestion) {
        return new Game(UUID.randomUUID(), type, currentQuestion);
    }
}
