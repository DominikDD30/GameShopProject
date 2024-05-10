package com.project.gameHubBackend.business;

import com.project.gameHubBackend.business.dao.PlatformDao;
import com.project.gameHubBackend.domain.Platform;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlatformService {

    private PlatformDao platformDao;

    public List<Platform> getPlatforms() {
        return platformDao.getPlatforms();
    }

    public void savePlatform(Platform platform) {
        platformDao.savePlatform(platform);
    }
}
