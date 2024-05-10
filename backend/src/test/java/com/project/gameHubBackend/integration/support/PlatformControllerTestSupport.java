package com.project.gameHubBackend.integration.support;

import com.project.gameHubBackend.api.dto.PlatformsDTO;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;

import static com.project.gameHubBackend.api.controller.PlatformController.PLATFORMS;

public interface PlatformControllerTestSupport {

    RequestSpecification requestSpecification();

    default PlatformsDTO getPlatforms(){
        return requestSpecification()
                .get(PLATFORMS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(PlatformsDTO.class);
    }
}
