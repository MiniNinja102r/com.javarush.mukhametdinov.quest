package com.javarush.quest.service;

import com.javarush.quest.entity.Game;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface GameService {
    @Nullable
    Game getById(UUID id);

    void remove(UUID id);

    void launch(Game game);
}
