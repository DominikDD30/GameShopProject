package com.project.gameHubBackend.integration.support;

import com.project.gameHubBackend.api.dto.GamesDTO;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static com.project.gameHubBackend.api.controller.GameController.GAMES;

public interface GameControllerTestSupport {

    RequestSpecification requestSpecification();

    default GamesDTO getGames() {
        return requestSpecification()
                .given()
                .params(Map.of(
                        "platform", "PC",
                        "priceFrom", new BigDecimal("10"),
                        "priceTo", new BigDecimal("25"),
                        "sortDirection", "DESC",
                        "sortColumn", "name",
                        "page",0
                        ))
                        .when()
                        .get(GAMES)
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .and()
                        .extract()
                        .as(GamesDTO.class);
    }
}
