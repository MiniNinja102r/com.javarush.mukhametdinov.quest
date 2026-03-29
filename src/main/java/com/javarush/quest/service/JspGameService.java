package com.javarush.quest.service;

import com.javarush.quest.entity.Game;
import com.javarush.quest.exception.GameNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class JspGameService implements GameService {
    final ConcurrentMap<UUID, Game> activeGames = new ConcurrentHashMap<>();

    @Override
    @Nullable
    public Game getById(UUID id) {
        return id == null ? null : this.activeGames.get(id);
    }

    @Override
    public void remove(UUID id) {
        if (id != null)
            this.activeGames.remove(id);
    }

    @Override
    public void launch(Game game) {
        if (game == null)
            throw new GameNotFoundException("JspGameService передал null вместо обьекта игры.");
        this.activeGames.put(game.getId(), game);
    }
}
