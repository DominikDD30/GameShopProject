package com.project.gameHubBackend.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"publisherGameId"})
@ToString(of = {"publisherGameId"})
public class PublisherGame {
     Integer publisherGameId;
     Publisher publisher;
     Game game;
}
