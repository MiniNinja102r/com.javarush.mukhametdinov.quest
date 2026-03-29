package com.javarush.quest.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Getter
public final class Game {
    final UUID id;

    @NotNull
    final GameType gameType;

    @NotNull
    @Setter
    Question currentQuestion;
}
