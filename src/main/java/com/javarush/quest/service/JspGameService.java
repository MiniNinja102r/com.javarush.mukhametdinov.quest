package com.javarush.quest.service;

import com.javarush.quest.entity.Game;
import com.javarush.quest.exception.GameNotFoundException;

public final class JspGameService implements GameService {

    @Override
    public void launch(Game game) {
        if (game == null)
            throw new GameNotFoundException("JspGameService передал null вместо обьекта игры.");
    }
}
