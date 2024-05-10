package com.project.gameHubBackend.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {

    Integer gameId;
    String gameNumber;
    private  String name;
    private  String mainPhoto;
    private String trailerPreviewImage;
    private String trailerUrl;
    private List<String>platforms;
    private BigDecimal price;
}

