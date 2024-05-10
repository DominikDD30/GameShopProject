package com.project.gameHubBackend.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GamePlatformDTO {
    private  String name;
    private String price;
    private Integer leftInStock;
}
