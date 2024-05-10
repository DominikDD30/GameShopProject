package com.project.gameHubBackend.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"publisherName"})
@ToString(of = {"publisherId","publisherName"})
public class Publisher {

     Integer publisherId;
     String publisherName;
     Set<PublisherGame> publisherGames;

}
