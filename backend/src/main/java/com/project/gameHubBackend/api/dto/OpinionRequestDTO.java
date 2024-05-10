package com.project.gameHubBackend.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpinionRequestDTO {

    private String customerEmail;
    private String gameName;
    private String description;

    @Min(1L)
    @Max(5L)
    private Short stars;
}
