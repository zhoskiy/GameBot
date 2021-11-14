package com.example.gamebot.service;

import com.example.gamebot.entity.Game;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jvnet.hk2.annotations.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final int COUNT_OF_GAMES = 5;

    public List<Game> findTopGames(String genre) {
        String urlWithFilters = getUrlWithFilters(genre);
        List<String> gameUrls = findTopGameUrls(urlWithFilters);

        return gameUrls.stream().map(this::parseGamePage).collect(Collectors.toList());
    }

    @SneakyThrows
    private Game parseGamePage(String gameUrl) {
        Document document = Jsoup.connect(gameUrl).get();

        String name = document.getElementById("appHubAppName").text();
        String imageUrl = document.getElementsByClass("game_header_image_full").first().attr("src");

        List<String> genres = document.getElementById("genresAndManufacturer")
                .getElementsByAttribute("href")
                .stream()
                .filter(genre -> genre.toString().contains("genre"))
                .toList()
                .stream().map(Element::text)
                .toList();

        String description = document.getElementsByClass("game_description_snippet").first().text();

        String rating = null;
        if (document.getElementsByClass("nonresponsive_hidden responsive_reviewdesc").first() != null) {
            rating = document.getElementsByClass("nonresponsive_hidden responsive_reviewdesc").first().text();
        }
        String releaseDate = document.getElementsByClass("release_date").first().getElementsByClass("date").text();


        return new Game(name, description, genres, gameUrl, imageUrl, releaseDate, rating);
    }

    @SneakyThrows
    private List<String> findTopGameUrls(String url) {
        Document document = Jsoup.connect(url).get();

        Elements links = document.getElementById("search_resultsRows").getAllElements();

        return links
                .stream()
                .map(element -> element.attr("href"))
                .distinct()
                .skip(1)
                .limit(COUNT_OF_GAMES)
                .collect(Collectors.toList());
    }

    private String getUrlWithFilters(String genre) {
        return switch (genre) {
            case "стратегия" -> "https://store.steampowered.com/search/?tags=9&category1=998&filter=topsellers";
            case "экшэн" -> "https://store.steampowered.com/search/?tags=19&category1=998&filter=topsellers";
            case "приключения" -> "https://store.steampowered.com/search/?tags=21&category1=998&filter=topsellers";
            case "инди" -> "https://store.steampowered.com/search/?tags=492&category1=998&filter=topsellers";
            default -> "https://store.steampowered.com/search/?category1=998&filter=topsellers";
        };
    }
}
