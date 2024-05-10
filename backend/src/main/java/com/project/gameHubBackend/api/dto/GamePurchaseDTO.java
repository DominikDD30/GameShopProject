package com.project.gameHubBackend.api.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GamePurchaseDTO {
    private String game;
    private String gameImage;
    private Integer quantity;
    private String platform;
    private BigDecimal price;
    private OpinionDTO opinion;
}
