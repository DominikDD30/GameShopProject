package com.project.gameHubBackend.integration;

import com.project.gameHubBackend.api.dto.PlatformDTO;
import com.project.gameHubBackend.api.dto.PlatformsDTO;
import com.project.gameHubBackend.api.dto.mapper.PlatformMapper;
import com.project.gameHubBackend.infrastructure.database.entity.PlatformEntity;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.PlatformJpaRepository;
import com.project.gameHubBackend.infrastructure.database.repository.mapper.PlatformEntityMapper;
import com.project.gameHubBackend.integration.configuration.RestAssuredIntegrationTestBase;
import com.project.gameHubBackend.integration.support.PlatformControllerTestSupport;
import com.project.gameHubBackend.util.EntityFixturesT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class PlatformControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
implements PlatformControllerTestSupport
{

    @Autowired
    private PlatformJpaRepository platformJpaRepository;
    @Autowired
    private PlatformEntityMapper platformEntityMapper;
    @Autowired
    private PlatformMapper platformMapper;

    @Test
    void thatPlatformUpdateWorksCorrectly(){
        //given
        PlatformEntity somePlatform1 = EntityFixturesT.getSomePlatform1();
        PlatformEntity somePlatform2 = EntityFixturesT.getSomePlatform2();
        platformJpaRepository.saveAll(List.of(somePlatform1, somePlatform2));

        PlatformDTO platformDTO1 = platformMapper.map(platformEntityMapper.mapFromEntity(somePlatform1));
        PlatformDTO platformDTO2 = platformMapper.map(platformEntityMapper.mapFromEntity(somePlatform2));
        //when
        PlatformsDTO platformsDTO = getPlatforms();
        //then
        Assertions.assertThat(platformsDTO.getResults())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("platformId")
                .containsExactlyInAnyOrder(platformDTO1,platformDTO2);
    }




}
