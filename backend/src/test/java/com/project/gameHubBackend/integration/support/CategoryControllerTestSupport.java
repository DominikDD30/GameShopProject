package com.project.gameHubBackend.integration.support;

import com.project.gameHubBackend.api.dto.CategoriesDTO;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;

import static com.project.gameHubBackend.api.controller.CategoryController.CATEGORIES;

public interface CategoryControllerTestSupport {

    RequestSpecification requestSpecification();

    default CategoriesDTO getCategories(){
        return requestSpecification()
                .get(CATEGORIES)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CategoriesDTO.class);
    }
}
