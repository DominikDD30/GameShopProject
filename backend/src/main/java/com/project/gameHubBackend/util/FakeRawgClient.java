package com.project.gameHubBackend.util;

import com.project.gameHubBackend.infrastructure.rawg.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FakeRawgClient {



    public List<RawgGame> getRawgGames(Integer batchNumber) {
        RawgGame rawgGame1 = RawgFixtures.getRawgGame1();

        RawgGame rawgGame2 = RawgFixtures.getRawgGame2();

        RawgGame rawgGame3 = RawgFixtures.getRawgGame3();

        if(batchNumber==0){
            return List.of(rawgGame1,rawgGame2);
        }
        else if(batchNumber ==1){
           return List.of(rawgGame3);
        }
        else throw new RuntimeException();
    }




    public List<RawgGenre> getRawgGenres() {
        return List.of(RawgFixtures.getSomeRawgGenre1(), RawgFixtures.getSomeRawgGenre2(), RawgFixtures.getSomeRawgGenre3());
    }

    public List<RawgPlatform> getRawgPlatforms() {
        return List.of(RawgFixtures.getSomeRawgPlatform1(), RawgFixtures.getSomeRawgPlatform2(), RawgFixtures.getSomeRawgPlatform3());
    }


}
