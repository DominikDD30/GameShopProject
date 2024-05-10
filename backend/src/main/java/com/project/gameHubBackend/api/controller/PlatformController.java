package com.project.gameHubBackend.api.controller;


import com.project.gameHubBackend.api.dto.PlatformsDTO;
import com.project.gameHubBackend.api.dto.mapper.PlatformMapper;
import com.project.gameHubBackend.business.PlatformService;
import com.project.gameHubBackend.domain.Platform;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.project.gameHubBackend.api.controller.PlatformController.PLATFORMS;


@RestController
@AllArgsConstructor
@RequestMapping(PLATFORMS)
public class PlatformController {

    public static final String PLATFORMS="/platforms";
    private PlatformService platformService;
    private PlatformMapper platformMapper;

    @GetMapping
    public PlatformsDTO getPlatforms(){
        List<Platform> platforms = platformService.getPlatforms();
        return  new PlatformsDTO(platforms.stream().map(platformMapper::map).toList());
    }
}
