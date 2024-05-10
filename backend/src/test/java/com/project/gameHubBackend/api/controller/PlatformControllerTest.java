package com.project.gameHubBackend.api.controller;

import com.project.gameHubBackend.api.dto.PlatformsDTO;
import com.project.gameHubBackend.api.dto.mapper.PlatformMapper;
import com.project.gameHubBackend.business.PlatformService;
import com.project.gameHubBackend.domain.Platform;
import com.project.gameHubBackend.util.DTOFixturesT;
import com.project.gameHubBackend.util.FixturesT;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlatformControllerTest {

    @Mock
    private PlatformService platformService;

    @Mock
    private PlatformMapper platformMapper;

    @InjectMocks
    private PlatformController platformController;



    @Test
    void thatGetPlatformsWorkCorrectly() {
        //given
        when(platformService.getPlatforms())
                .thenReturn(List.of(FixturesT.getSomePlatform1(), FixturesT.getSomePlatform2(), FixturesT.getSomePlatform3()));

        when(platformMapper.map(any(Platform.class)))
                .thenReturn(DTOFixturesT.getSomePlatformDTO1())
                .thenReturn(DTOFixturesT.getSomePlatformDTO2())
                .thenReturn(DTOFixturesT.getSomePlatformDTO3());
        //when
        PlatformsDTO result = platformController.getPlatforms();
        //then
        assertThat(result).isEqualTo(
                PlatformsDTO.builder()
                        .results(List.of(DTOFixturesT.getSomePlatformDTO1(), DTOFixturesT.getSomePlatformDTO2(), DTOFixturesT.getSomePlatformDTO3()))
                        .build());
    }
}