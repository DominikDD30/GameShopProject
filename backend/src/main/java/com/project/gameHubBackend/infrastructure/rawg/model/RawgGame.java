package com.project.gameHubBackend.infrastructure.rawg.model;

import lombok.*;

import java.util.List;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RawgGame {

    private Integer id;
    private String name;
    private String description_raw;
    private String background_image;
    private List<RawgGenre> genres;
    private List<RawgPlatformWrapper> parent_platforms;
    private RawgMovie movie;
    private List<RawgPhoto> short_screenshots;
    private List<RawgPublisher> publishers;
}
