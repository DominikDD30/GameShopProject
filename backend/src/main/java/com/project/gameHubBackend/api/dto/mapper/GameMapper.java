package com.project.gameHubBackend.api.dto.mapper;

import com.project.gameHubBackend.api.dto.*;
import com.project.gameHubBackend.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.*;

@Mapper(componentModel = "spring")
public interface GameMapper extends OffsetDateTimeMapper {

    default GameDTO map(Game game, String chosenPlatform) {
        return GameDTO.builder()
                .gameId(game.getGameId())
                .gameNumber(game.getGameNumber())
                .name(game.getName())
                .mainPhoto(game.getMainPhoto())
                .trailerPreviewImage(Objects.nonNull(game.getTrailer()) ? game.getTrailer().getPreviewImage() : null)
                .trailerUrl(Objects.nonNull(game.getTrailer()) ? game.getTrailer().getUrl() : null)
                .platforms(mapGameDtoPlatforms(game.getGamePlatforms()))
                .price(getPrice(game.getGamePlatforms(), chosenPlatform))
                .build();
    }

    private List<String> mapGameDtoPlatforms(Set<PlatformGame> gamePlatforms) {
        List<String> platformNames = new ArrayList<>(gamePlatforms.stream().map(gp -> gp.getPlatform().getName()).toList());

        for (PlatformGame platformGame : gamePlatforms) {
            String platform = platformGame.getPlatform().getName();
            if (platform.equals("Steam Key") && platformNames.contains("PC")) {
                platformNames.remove("Steam Key");
            } else if (platform.equals("PSN Key") && platformNames.contains("PlayStation")) {
                platformNames.remove("PSN Key");
            } else if (platform.equals("Xbox Live Key") && platformNames.contains("Xbox")) {
                platformNames.remove("Xbox Live Key");
            }
        }
        return platformNames;
    }


    @Mapping(source = "gameCategories", target = "gameCategories", qualifiedByName = "mapGameCategories")
    @Mapping(source = "photos", target = "photos", qualifiedByName = "mapPhotos")
    @Mapping(source = "publishers", target = "publishers", qualifiedByName = "mapPublishers")
    @Mapping(source = "trailer", target = "trailerPreview", qualifiedByName = "mapTrailerPreview")
    @Mapping(source = "trailer", target = "trailerUrl", qualifiedByName = "mapTrailerUrl")
    @Mapping(source = "gameOpinions", target = "opinions", qualifiedByName = "mapOpinions")
    GameDetailDTO mapToGameDetail(Game game);


    @Named("mapTrailerPreview")
    default String mapTrailerPreview(Trailer trailer) {
        return Objects.nonNull(trailer) ? trailer.getPreviewImage() : null;
    }

    @Named("mapTrailerUrl")
    default String mapTrailerUrl(Trailer trailer) {
        return Objects.nonNull(trailer) ? trailer.getUrl() : null;
    }

    @Named("mapPhotos")
    default List<PhotoDTO> mapPhotos(Set<Photo> photos) {
        return photos.stream()
                .map(this::map).toList();
    }

    @Named("mapOpinions")
    default List<OpinionDTO> mapOpinions(Set<OpinionCustomerGame> opinions) {
        return opinions.stream()
                .map(opinion -> new OpinionDTO(
                        opinion.getStars(),
                        opinion.getDescription(),
                        mapOffsetDateTimeToShortDateString(opinion.getDate()),
                        "" + opinion.getCustomer().getName().charAt(0) + "..."
                                + opinion.getCustomer().getSurname().charAt(0)
                )).toList();
    }

    @Named("mapPublishers")
    default List<String> mapPublishers(Set<PublisherGame> publishers) {
        return publishers.stream().map(publisherGame -> publisherGame.getPublisher().getPublisherName()).toList();
    }

    @Named("mapGameCategories")
    default List<CategoryDTO> mapGameCategories(Set<CategoryGame> categoryGames) {
        return categoryGames.stream()
                .map(CategoryGame::getCategory)
                .map(this::map).toList();
    }


    CategoryDTO map(Category category);

    PhotoDTO map(Photo photo);


    default GamePlatformDTO map(PlatformGame platformGame) {
        return new GamePlatformDTO(platformGame.getPlatform().getName()
                , platformGame.getPrice().toString(), platformGame.getLeftInStock());
    }

    private BigDecimal getPrice(Set<PlatformGame> gamePlatforms, String chosenPlatform) {
        Optional<PlatformGame> desiredPlatform = gamePlatforms.stream().
                filter(gp -> gp.getPlatform().getName().equals(chosenPlatform)).findAny();

        return desiredPlatform.isPresent() ?
                desiredPlatform.get().getPrice()
                : gamePlatforms.stream().findFirst().get().getPrice();
    }
}
