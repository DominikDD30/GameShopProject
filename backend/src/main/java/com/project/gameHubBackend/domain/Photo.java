package com.project.gameHubBackend.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"url"})
@ToString(of = {"photoId"})
public class Photo {

     Integer photoId;
     String url;
     Game game;
}
