package com.javarush.quest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Nullable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@ToString
public final class Question extends BaseEntity {
    final String text;
    final Answer[] answers;

    @Nullable
    final EndingType endingType;

    public Question(long id, String text, Answer[] answers, @Nullable EndingType endingType) {
        super(id);
        this.text = text;
        this.answers = answers;
        this.endingType = endingType;
    }
}
