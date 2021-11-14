package com.example.gamebot.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Game {
    private final String name;
    private final String description;
    private final List<String> genres;
    private final String url;
    private final String imageUrl;
    private final String releaseDateTime;
    private final String rating;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Название:   ").append(name).append("\n");
        sb.append("Описание:\n   ").append(description).append("\n\n");
        sb.append("Жанр:   ");
        genres.forEach(sb::append);
        sb.append("\n");
        sb.append("Дата релиза:   ").append(releaseDateTime).append("\n");
        sb.append("Рейтинг:   ").append(rating).append("\n\n\n");
        sb.append(url).append("\n");

        return sb.toString();
    }
}
