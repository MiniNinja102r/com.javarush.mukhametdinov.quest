package com.javarush.quest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum GameType {
    ESCAPE_FROM_MURDER("maniac_escape.json");

    @Getter
    final String jsonGameFile;
}
