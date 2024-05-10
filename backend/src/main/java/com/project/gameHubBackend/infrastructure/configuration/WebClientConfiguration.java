package com.project.gameHubBackend.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.netty.http.client.HttpClient;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfiguration {

    @Value("${api.rawg.url}")
    private String RAWG_URL;
    @Value("${api.rawg.apikey}")
    private  String RAWG_API_KEY;

    @Value("${api.google.url}")
    private String GOOGLE_URL;
    @Value("${api.google.apikey}")
    private  String GOOGLE_API_KEY;
    @Value("${api.google.cseid}")
    private  String GOOGLE_CSE_ID;

    private static final int TIMEOUT=5000;



    @Bean
    public WebClient rawgWebClient(ObjectMapper objectMapper) {
        final var httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
                .responseTimeout(Duration.ofMillis(TIMEOUT))
                .doOnConnected(con ->
                        con.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS)));


        return WebClient.builder()
                .baseUrl(RAWG_URL)
                .exchangeStrategies(buildDefaultExchangeStrategies(objectMapper))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .filter(((request, next) -> {
                    String uriWithQueryParam = UriComponentsBuilder.fromUri(request.url())
                            .queryParam("key", RAWG_API_KEY)
                            .build().toUriString();
                    ClientRequest newRequest = ClientRequest.from(request)
                            .url(URI.create(uriWithQueryParam))
                            .build();
                    return next.exchange(newRequest);
                }))
                .build();
    }


    @Bean
    public WebClient googleWebClient(ObjectMapper objectMapper) {
        return WebClient.builder()
                .exchangeStrategies(buildDefaultExchangeStrategies(objectMapper))
                .baseUrl(GOOGLE_URL)
                .filter(((request, next) -> {
                    String uriWithQueryParam = UriComponentsBuilder.fromUri(request.url())
                            .queryParam("key", GOOGLE_API_KEY)
                            .queryParam("cx", GOOGLE_CSE_ID)
                            .build().toUriString();
                    ClientRequest newRequest = ClientRequest.from(request)
                            .url(URI.create(uriWithQueryParam))
                            .build();
                    return next.exchange(newRequest);
                }))
                .build();
    }




    private ExchangeStrategies buildDefaultExchangeStrategies(ObjectMapper objectMapper) {
        return ExchangeStrategies
                .builder()
                .codecs(configurer -> {
                    configurer
                            .defaultCodecs()
                            .jackson2JsonEncoder(new Jackson2JsonEncoder(
                                    objectMapper,
                                    MediaType.APPLICATION_JSON
                            ));
                    configurer
                            .defaultCodecs()
                            .jackson2JsonDecoder(new Jackson2JsonDecoder(
                                    objectMapper,
                                    MediaType.APPLICATION_JSON
                            ));
                }).build();
    }

}
