package com.javarush.quest.repository;

import com.javarush.quest.entity.GameType;
import com.javarush.quest.entity.Question;
import org.jetbrains.annotations.NotNull;

public interface GameRepository {
    Question read(@NotNull GameType type, long id);
}
