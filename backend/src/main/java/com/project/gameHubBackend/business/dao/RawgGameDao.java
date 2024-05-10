package com.project.gameHubBackend.business.dao;

import com.project.gameHubBackend.infrastructure.rawg.model.*;

import java.util.List;
import java.util.Optional;

public interface RawgGameDao {


    Optional<List<RawgGame>> getRawgGames(int page);
    Optional<RawgGameDetail> getRawgGameDetail(int gameId);

    Optional<List<RawgGenre>> getRawgGenres();

    Optional<List<RawgPlatform>> getRawgPlatforms();

    Optional<RawgMovie> getRawgGameMovie(int gameId);
}
