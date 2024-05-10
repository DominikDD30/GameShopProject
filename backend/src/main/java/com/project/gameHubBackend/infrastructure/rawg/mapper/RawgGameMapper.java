package com.project.gameHubBackend.infrastructure.rawg.mapper;

import com.project.gameHubBackend.domain.Game;
import com.project.gameHubBackend.domain.PublisherGame;
import com.project.gameHubBackend.domain.Trailer;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgGame;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgMovie;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgPlatformWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RawgGameMapper {

    private RawgMovieMapper rawgMovieMapper;
    private RawgPhotoMapper rawgPhotoMapper;
    private RawgPublisherMapper rawgPublisherMapper;

    public Game mapToGame(RawgGame rawgGame) {

        Game game = Game.builder()
                .name(rawgGame.getName())
                .description(rawgGame.getDescription_raw())
                .mainPhoto(rawgGame.getBackground_image())
                .isContinuouslyDelivered(true)
                .isSoldOut(false)
                .trailer(Optional.ofNullable(rawgGame.getMovie()).map(rawgMovieMapper::mapToTrailer).orElse(null))
                .photos(rawgGame.getShort_screenshots().stream()
                        .map(rawgPhoto -> rawgPhotoMapper.mapToPhoto(rawgPhoto)).collect(Collectors.toSet()))
                .build();

        return game.withPublishers(rawgGame.getPublishers().stream()
                .map(rawgPublisher -> rawgPublisherMapper.mapToPublisher(rawgPublisher))
                .map(publisher-> PublisherGame.builder().publisher(publisher).game(game).build())
                .collect(Collectors.toSet()));
    }
}
