package com.itransition.courseproject.enums;

import lombok.Getter;

@Getter
public enum FieldTypeEnum {
    STRING("String"),
    INTEGER("Integer"),
    BOOLEAN("Boolean"),
    DATE("Date");

    private final String name;

    FieldTypeEnum(String name) {
        this.name = name;
    }
}
