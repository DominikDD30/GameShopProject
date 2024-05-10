package com.project.gameHubBackend.business.purchaseService;

import com.project.gameHubBackend.business.CustomerService;
import com.project.gameHubBackend.business.GameService;
import com.project.gameHubBackend.domain.*;
import com.project.gameHubBackend.infrastructure.database.repository.PurchaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.project.gameHubBackend.util.Functions.*;

@Slf4j
@Service
public class PurchaseServiceForSimulation extends PurchaseService{
    public PurchaseServiceForSimulation(CustomerService customerService, GameService gameService, PurchaseRepository purchaseRepository) {
        super(customerService, gameService, purchaseRepository);
    }

    public void simulatePurchasesDateToCompleted(){
        List<Purchase> purchases = purchaseRepository.getPurchasesWithoutDateCompleted();
        for (Purchase purchase:purchases){
            purchase.setStatus(OrderStatus.DELIVERED);
            LocalDate localDate = purchase.getDateStarted().toLocalDate();
            LocalTime localTime = purchase.getDateStarted().toLocalTime();

            OffsetDateTime dateCompleted = OffsetDateTime.of(
                    purchase.getDeliveryType().equals(DeliveryType.ELECTRONIC_SHIPMENT) ?
                            localDate :
                            localDate.with(localDate.plus(getRandomNumber(1, 3), ChronoUnit.DAYS)),
                    purchase.getDeliveryType().equals(DeliveryType.ELECTRONIC_SHIPMENT) ?
                            localTime :
                            localTime.with(localTime.plus(getRandomNumber(3, 8), ChronoUnit.HOURS)),
                    ZoneOffset.UTC);
            purchaseRepository.setDateCompleted(purchase,dateCompleted);
        }
    }


    @Override
    protected Purchase buildPurchase(PurchaseRequest purchaseRequest) {
        LocalDate localDate = LocalDate.of(getRandomNumber(2018, 2023), getRandomNumber(1, 12), getRandomNumber(1, 28));
        LocalTime localTime = LocalTime.of(getRandomNumber(0, 23), getRandomNumber(1, 59));
        OffsetDateTime dateStarted = OffsetDateTime.of(localDate,localTime, ZoneOffset.UTC);
        OffsetDateTime dateCompleted = OffsetDateTime.of(
                purchaseRequest.getDeliveryType().equals(DeliveryType.ELECTRONIC_SHIPMENT)?
                        localDate :
                        localDate.with(localDate.plus(getRandomNumber(1,3), ChronoUnit.DAYS)),
                purchaseRequest.getDeliveryType().equals(DeliveryType.ELECTRONIC_SHIPMENT)?
                        localTime :
                        localTime.with(localTime.plus(getRandomNumber(3,8),ChronoUnit.HOURS)),
                ZoneOffset.UTC);
        if(dateCompleted.getYear()==2023 && dateCompleted.getMonthValue() ==12){
            dateCompleted=dateCompleted.withDayOfMonth(getRandomNumber(1,LocalDate.now().getDayOfMonth()));
        }
        return super.buildPurchase(purchaseRequest).withDateStarted(dateStarted).withDateCompleted(dateCompleted).withStatus(OrderStatus.DELIVERED);
    }


}
