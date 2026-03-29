package com.javarush.quest.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public abstract class BaseEntity {
    @Getter(value = AccessLevel.PUBLIC)
    final long id;
}
