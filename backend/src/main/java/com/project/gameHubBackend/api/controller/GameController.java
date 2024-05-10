package com.project.gameHubBackend.api.controller;

import com.project.gameHubBackend.api.dto.GameDetailDTO;
import com.project.gameHubBackend.api.dto.GamesDTO;
import com.project.gameHubBackend.api.dto.mapper.GameMapper;
import com.project.gameHubBackend.business.GameService;
import com.project.gameHubBackend.domain.Game;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.project.gameHubBackend.api.controller.GameController.GAMES;
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(GAMES)
public class GameController {

    public static final String GAMES="/games";
    private GameService gameService;
    private GameMapper gameMapper;

    @GetMapping
    public GamesDTO getGames(
            @RequestParam(name = "category", required = false) Optional<String> category,
            @RequestParam(name = "platform", required = false) Optional<String> platform,
            @RequestParam(name = "searchText", required = false) Optional<String> searchText,
            @RequestParam(name = "priceFrom", required = false) Optional<String> priceFrom,
            @RequestParam(name = "priceTo", required = false) Optional<String> priceTo,
            @RequestParam(name = "sortDirection", required = false) Optional<String> sortDirection,
            @RequestParam(name = "sortColumn", required = false) Optional<String> sortColumn,
            @RequestParam(name = "page") Integer page
            ){
        return new GamesDTO(
                gameService.getGames(category, platform, searchText, priceFrom, priceTo,sortDirection,sortColumn,page).stream()
                        .map((Game game) -> gameMapper.map(game, platform.orElse("PC")))
                        .toList());
    }

    @GetMapping("/count")
    public Integer getAllGamesCount(@RequestParam(name = "category", required = false) Optional<String> category,
                                    @RequestParam(name = "platform", required = false) Optional<String> platform,
                                    @RequestParam(name = "priceFrom", required = false) Optional<String> priceFrom,
                                    @RequestParam(name = "priceTo", required = false) Optional<String> priceTo,
                                    @RequestParam(name = "searchText", required = false) Optional<String> searchText){
        return gameService.getAllGamesCount(category,platform,searchText,priceFrom,priceTo);
    }
     @GetMapping("/{gameId}")
    public GameDetailDTO getGameDetails(@PathVariable Integer gameId){
       Game game = gameService.getGameById(gameId);
       return gameMapper.mapToGameDetail(game);
    }

}
