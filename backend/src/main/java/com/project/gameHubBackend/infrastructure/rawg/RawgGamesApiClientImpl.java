package com.project.gameHubBackend.infrastructure.rawg;

import com.project.gameHubBackend.business.dao.RawgGameDao;
import com.project.gameHubBackend.infrastructure.rawg.model.*;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RawgGamesApiClientImpl implements RawgGameDao {


    private final WebClient rawgWebClient;


    @Override
    public Optional<List<RawgGame>> getRawgGames(int page) {
        try {
            var response = rawgWebClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/games")
                            .queryParam("page", page)
                            .queryParam("page_size",15)
                            .build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<FetchResponse<RawgGame>>() {})
                    .map(FetchResponse::getResults)
                    .block();

            return Optional.ofNullable(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<RawgGameDetail> getRawgGameDetail(int gameId) {
        try {
            var response = rawgWebClient.get()
                    .uri("/games/"+gameId)
                    .retrieve()
                    .bodyToMono(RawgGameDetail.class)
                    .block();

            return Optional.ofNullable(response);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<RawgGenre>> getRawgGenres() {
        try {
            var response = rawgWebClient.get()
                    .uri("/genres")
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<FetchResponse<RawgGenre>>() {})
                    .map(FetchResponse::getResults)
                    .block();

            return Optional.ofNullable(response);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<RawgPlatform>> getRawgPlatforms() {
        try {
            var response = rawgWebClient.get()
                    .uri("/platforms/lists/parents")
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<FetchResponse<RawgPlatform>>() {})
                    .map(FetchResponse::getResults)
                    .block();

            return Optional.ofNullable(response);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<RawgMovie> getRawgGameMovie(int gameId) {
        try {
            var response = rawgWebClient.get()
                    .uri("/games/"+gameId+"/movies")
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<FetchResponse<RawgMovie>>() {})
                    .map(fetchResponse -> fetchResponse.getResults().get(0))
                    .block();

            return Optional.ofNullable(response);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}
