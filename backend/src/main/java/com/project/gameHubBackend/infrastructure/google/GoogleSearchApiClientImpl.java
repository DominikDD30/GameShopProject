package com.project.gameHubBackend.infrastructure.google;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
@AllArgsConstructor
public class GoogleSearchApiClientImpl {

    private final WebClient googleWebClient;


    public String searchGoogle(String request) throws RuntimeException {

        try {
        return googleWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q",request)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        }catch (WebClientResponseException e){
            throw new RuntimeException("Google api Exception");
        }catch (Exception e){
            throw new RuntimeException("Price Not Found");
        }
    }


}
