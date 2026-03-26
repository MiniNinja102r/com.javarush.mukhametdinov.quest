package com.javarush.quest.repository;

import com.javarush.quest.entity.Game;
import com.javarush.quest.entity.GameType;
import com.javarush.quest.util.Constants;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public final class GameFactory {

    @NotNull
    public static Game createGame() {
        return createGame(Constants.DEFAULT_GAME_TYPE);
    }

    @NotNull
    public static Game createGame(@NotNull GameType type) {
        return new Game(type);
    }
}
