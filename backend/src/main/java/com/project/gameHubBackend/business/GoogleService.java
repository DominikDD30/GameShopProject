package com.project.gameHubBackend.business;

import com.project.gameHubBackend.infrastructure.google.GoogleSearchApiClientImpl;
import com.project.gameHubBackend.util.Functions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
@Service
@AllArgsConstructor
public class GoogleService {

    private GoogleSearchApiClientImpl googleSearchApiClient;

    public BigDecimal searchForProductPrice(String googleRequest){
        try{
        String result = googleSearchApiClient.searchGoogle("ile kosztuje "+googleRequest);
        Pattern regex = Pattern.compile("(\\d+,\\d+ zł)|(\\d+,\\d+zł)|(\\d+zł)");
        Matcher matcher = regex.matcher(result);
            matcher.find();
            String matched = matcher.group();
            String transformed = matched.replace(" ", "").replace(",", ".").substring(0, matched.indexOf("zł") - 1);
            return new BigDecimal(transformed).divide(BigDecimal.valueOf(4.5), RoundingMode.HALF_UP);
        }catch (RuntimeException e){
            e.getSuppressed();
            log.warn(" "+e.getMessage());
            return BigDecimal.valueOf(Functions.getRandomNumber(20,40));
        }
    }


}
