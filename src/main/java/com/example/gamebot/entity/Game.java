package com.example.gamebot.entity;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class Game {
    private final String name;
    private final Genre genre;
    private final String url;
    private final String description;
    private final LocalDateTime releaseDateTime;
    private final String gameMode;
}
