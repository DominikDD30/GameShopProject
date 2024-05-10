package com.project.gameHubBackend.business;

import com.project.gameHubBackend.api.dto.GamePurchaseDTO;
import com.project.gameHubBackend.api.dto.OpinionDTO;
import com.project.gameHubBackend.api.dto.OpinionRequestDTO;
import com.project.gameHubBackend.api.dto.mapper.OpinionMapper;
import com.project.gameHubBackend.business.dao.OpinionDao;
import com.project.gameHubBackend.domain.Customer;
import com.project.gameHubBackend.domain.Game;
import com.project.gameHubBackend.domain.OpinionCustomerGame;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OpinionService {

    private OpinionDao opinionDao;
    private CustomerService customerService;
    private GameService gameService;
    private OpinionMapper opinionMapper;


    public void createOpinion(OpinionRequestDTO opinionRequest) {
        Customer customer = customerService.getCustomerByEmail(opinionRequest.getCustomerEmail()).get();
        Game game = gameService.getGameByName(opinionRequest.getGameName());

        Optional<OpinionCustomerGame> alreadyOpinion = opinionDao.getGameOpinionForSpecifiedCustomer(game.getName(), customer.getEmail());
        if (alreadyOpinion.isPresent()) {
            opinionDao.saveOpinion(alreadyOpinion.get()
                    .withStars(opinionRequest.getStars())
                    .withDescription(opinionRequest.getDescription())
                    .withDate(alreadyOpinion.get().getDate())
                    .withCustomer(customer)
                    .withGame(game)
            );
        } else {
            var opinion = OpinionCustomerGame.builder()
                    .customer(customer)
                    .game(game)
                    .stars(opinionRequest.getStars())
                    .description(opinionRequest.getDescription())
                    .date(OffsetDateTime.now())
                    .build();
            opinionDao.saveOpinion(opinion);
        }
    }

    public Optional<OpinionDTO> findCustomerOpinionForPurchase(GamePurchaseDTO gamePurchase, String customerEmail) {
        var opinion = getCustomerOpinionForGame(gamePurchase.getGame(), customerEmail);
        return opinion.isPresent() ? Optional.ofNullable(opinionMapper.mapToDTO(opinion.get()))
                : Optional.empty();
    }

    public Optional<OpinionCustomerGame> getCustomerOpinionForGame(String gameName, String customerEmail) {
        return opinionDao.getGameOpinionForSpecifiedCustomer(gameName, customerEmail);
    }
}
