package com.project.gameHubBackend.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDetailDTO {
    private String name;
    private String gameNumber;
    private String description;
    private String mainPhoto;
    private String trailerPreview;
    private String trailerUrl;
    private  Boolean isSoldOut;
    private List<PhotoDTO> photos;
    private List<CategoryDTO> gameCategories;
    private List<GamePlatformDTO> gamePlatforms;
    private List<String>publishers;
    private List<OpinionDTO>opinions;
}
