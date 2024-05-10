package com.project.gameHubBackend.integration.support;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import java.util.Map;

public interface WireMockTestSupport {

    default void stubForRawgGenres(final WireMockServer wireMockServer) {
        wireMockServer.stubFor(WireMock.get(WireMock.urlPathEqualTo("/genres"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wiremock/rawgGenres.json")
                        .withTransformers("response-template")));
    }

    default void stubForRawgPlatforms(final WireMockServer wireMockServer) {
        wireMockServer.stubFor(WireMock.get(WireMock.urlPathEqualTo("/platforms/lists/parents"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wiremock/rawgPlatforms.json")
                        .withTransformers("response-template")));
    }

    default void stubForRawgGames(final WireMockServer wireMockServer) {
        wireMockServer.stubFor(WireMock.get(WireMock.urlPathEqualTo("/games"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wiremock/rawgGames.json")
                        .withTransformers("response-template")));
    }


    default void stubForRawgMovie(final WireMockServer wireMockServer, int gameId) {
        wireMockServer.stubFor(WireMock.get(WireMock.urlPathEqualTo("/games/%s/movies".formatted(gameId)))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wiremock/rawgMovie.json")
                        .withTransformers("response-template")));
    }

    default void stubForRawgGameDetail(final WireMockServer wireMockServer, int gameId) {
        wireMockServer.stubFor(WireMock.get(WireMock.urlPathEqualTo("/games/"+gameId))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("wiremock/rawgGameDetail.json")
                        .withTransformerParameters(Map.of("gameId", gameId))
                        .withTransformers("response-template")));
    }
}
