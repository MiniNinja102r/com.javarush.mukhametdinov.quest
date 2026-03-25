package com.javarush.quest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@ToString
public final class Answer extends BaseEntity {
    final String text;
    final long nextQuestionId;

    public Answer(long id, String text, long nextQuestionId) {
        super(id);
        this.text = text;
        this.nextQuestionId = nextQuestionId;
    }
}
