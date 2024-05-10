package com.project.gameHubBackend.api.controller;

import com.project.gameHubBackend.api.dto.OpinionDTO;
import com.project.gameHubBackend.api.dto.OpinionRequestDTO;
import com.project.gameHubBackend.api.dto.mapper.OpinionMapper;
import com.project.gameHubBackend.business.OpinionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.project.gameHubBackend.api.controller.OpinionController.OPINIONS;

@RestController
@AllArgsConstructor
@RequestMapping(OPINIONS)
public class OpinionController {

    public static final String OPINIONS = "/opinions";

    private OpinionService opinionService;
    private OpinionMapper opinionMapper;

    @PostMapping
    public void makeOpinion(@RequestBody OpinionRequestDTO opinionRequest){
        opinionService.createOpinion(opinionRequest);
    }

    @GetMapping("/customers/{customerEmail}/games/{gameName}")
    public OpinionDTO getCustomerOpinionForGame(@PathVariable String customerEmail, @PathVariable String gameName){
        return opinionService.getCustomerOpinionForGame(gameName,customerEmail)
                .map(opinionMapper::mapToDTO).orElse(OpinionDTO.builder().build());
    }
}
