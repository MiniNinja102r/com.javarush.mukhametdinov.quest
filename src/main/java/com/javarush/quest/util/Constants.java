package com.javarush.quest.util;

import com.javarush.quest.entity.GameType;

public interface Constants {
    GameType DEFAULT_GAME_TYPE = GameType.ESCAPE_FROM_MURDER;

    String USERNAME = "username";
    String QUESTION = "question";
    String GAME_TYPE = "gameType";
    String GAME_UUID = "gameUUID";
    String GAMES_COUNT = "gamesCount";

    long FIRST_QUESTION_ID = 1;
}
