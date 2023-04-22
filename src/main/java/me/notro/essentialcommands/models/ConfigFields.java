package me.notro.essentialcommands.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ConfigFields {
    EXECUTOR("executor"), // UUID
    REASON("reason"); // String

    @Getter
    private final String message;
}
