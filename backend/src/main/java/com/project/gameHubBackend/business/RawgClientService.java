package com.project.gameHubBackend.business;

import com.project.gameHubBackend.business.dao.RawgGameDao;
import com.project.gameHubBackend.infrastructure.rawg.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RawgClientService {

    private RawgGameDao rawgGameDao;


    public List<RawgGenre> getRawgGenres(){
        return rawgGameDao.getRawgGenres()
                .orElseThrow(()->new RuntimeException("failed to Fetch Genres from Rawg"));
    }

    public List<RawgPlatform> getRawgPlatforms(){
        return rawgGameDao.getRawgPlatforms()
                .orElseThrow(()->new RuntimeException("failed to Fetch Platforms from Rawg"));
    }


    public List<RawgGame> getRawgGames(int page) {
        List<RawgGame> rawgGames = rawgGameDao.getRawgGames(page)
                .orElseThrow(()->new RuntimeException("failed to Fetch Games from Rawg"));

        rawgGames.forEach(this::buildRawgGame);
        return rawgGames;
    }

    private void buildRawgGame(RawgGame rawgGame) {
        Optional<RawgGameDetail> rawgGameDetail = rawgGameDao.getRawgGameDetail(rawgGame.getId());
        Optional<RawgMovie> rawgGameMovie = rawgGameDao.getRawgGameMovie(rawgGame.getId());

        rawgGameDetail.ifPresent(gameDetail ->{
                rawgGame.setDescription_raw(gameDetail.description_raw());
                rawgGame.setPublishers(gameDetail.publishers());
        });
       rawgGameMovie.ifPresent(rawgGame::setMovie);
    }

}
