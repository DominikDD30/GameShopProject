package com.project.gameHubBackend.business.purchaseService;

import com.project.gameHubBackend.business.CustomerService;
import com.project.gameHubBackend.business.GameService;
import com.project.gameHubBackend.domain.*;
import com.project.gameHubBackend.domain.exception.InvalidInputDataException;
import com.project.gameHubBackend.domain.exception.LeftInStockBelowZeroException;
import com.project.gameHubBackend.infrastructure.database.repository.PurchaseRepository;
import com.project.gameHubBackend.util.Fixtures;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
@AllArgsConstructor
public class PurchaseService {

    private CustomerService customerService;
    private GameService gameService;

    protected PurchaseRepository purchaseRepository;


    public List<Purchase> getPurchasesForCustomer(String customerEmail) {
        return purchaseRepository.getPurchasesForCustomer(customerEmail);
    }

    @Transactional
    public void changePurchaseStatus(String customerEmail, String purchaseNumber, OrderStatus orderStatus) {
        purchaseRepository.changePurchaseStatus(customerEmail,purchaseNumber,orderStatus);
    }

    @Transactional
    public Purchase makePurchase(PurchaseRequest purchaseRequest) {
        validatePurchaseRequest(purchaseRequest);
        try{
            if(isNullOrEmpty(purchaseRequest.getCustomerEmail())){
                Purchase purchase = processBuyWithoutAccount(purchaseRequest);
                gameService.decreaseLeftInStock(purchaseRequest.getGames());
                return purchase;
            }

        Purchase purchase = customerService.getCustomerByEmail(purchaseRequest.getCustomerEmail()).map(
                customer -> processBuyByExistingUser(customer, purchaseRequest)
        ).orElseGet(() -> processBuyForTheFirstTime(purchaseRequest));

        gameService.decreaseLeftInStock(purchaseRequest.getGames());

        return purchase;
        }catch (LeftInStockBelowZeroException | ObjectOptimisticLockingFailureException e){
        log.error(e.getMessage());
        throw e;
        }
    }

    protected void validatePurchaseRequest(PurchaseRequest purchaseRequest) {
        DeliveryType deliveryType = purchaseRequest.getDeliveryType();
        boolean isPostalOrCourierDelivery = deliveryType.equals(DeliveryType.POSTAL) || deliveryType.equals(DeliveryType.COURIER);

        if(deliveryType.equals(DeliveryType.ELECTRONIC_SHIPMENT) && isNullOrEmpty(purchaseRequest.getDeliveryEmail())){
                throw new InvalidInputDataException("delivery email is empty");
        }
        else if (deliveryType.equals(DeliveryType.PICKUP_POINT) && isNullOrEmpty(purchaseRequest.getPickupPointName())) {
            throw new InvalidInputDataException("pickup point name was null for chosen PickupPoint Delivery");
        }
        else if (isPostalOrCourierDelivery &&
                (isNullOrEmpty(purchaseRequest.getCustomerAddressPostalCode()) ||
                        isNullOrEmpty(purchaseRequest.getCustomerAddressCity()) ||
                        isNullOrEmpty(purchaseRequest.getCustomerAddressStreet()))) {
            throw new InvalidInputDataException("address data was null for chosen Postal/Courier Delivery");
        }
    }

    private Purchase processBuyWithoutAccount(PurchaseRequest purchaseRequest) {
        boolean isPostalOrCourierDelivery = purchaseRequest.getDeliveryType().equals(DeliveryType.POSTAL)
                || purchaseRequest.getDeliveryType().equals(DeliveryType.COURIER);
        List<GamePurchase> gamePurchases = buildGamePurchases(purchaseRequest);
        Purchase purchase = buildPurchase(purchaseRequest);
        gamePurchases.forEach(purchase::addGamePurchase);
        Customer customer = isPostalOrCourierDelivery ?
                Customer.builder().name(purchaseRequest.getCustomerName()).surname(purchaseRequest.getCustomerSurname()).build()
                : Customer.builder().build();
        customerService.issuePurchase(customer.withPurchases(List.of(purchase)));
        return purchase;
    }
    protected Purchase processBuyForTheFirstTime(PurchaseRequest purchaseRequest) {
        List<GamePurchase> gamePurchases = buildGamePurchases(purchaseRequest);


        Customer customer = Customer.builder()
                .name(purchaseRequest.getCustomerName())
                .surname(purchaseRequest.getCustomerSurname())
                .email(purchaseRequest.getCustomerEmail())
                .build();
        Purchase purchase = buildPurchase(purchaseRequest);
        gamePurchases.forEach(purchase::addGamePurchase);

        customerService.issuePurchase(customer.withPurchases(List.of(purchase)));
        return purchase;
    }
    protected Purchase processBuyByExistingUser(Customer customer, PurchaseRequest purchaseRequest) {
        List<GamePurchase> gamePurchases = buildGamePurchases(purchaseRequest);

        Purchase purchase = buildPurchase(purchaseRequest);
        gamePurchases.forEach(purchase::addGamePurchase);

        List<Purchase> existingPurchases = customer.getPurchases();
        existingPurchases.add(purchase);
        customerService.issuePurchase(customer.withPurchases(existingPurchases));
        return purchase;
    }

    protected List<GamePurchase> buildGamePurchases(PurchaseRequest purchaseRequest) {
        return purchaseRequest.getGames().stream()
                .map(purchaseGame -> {
                    Game game = gameService.getGameByName(purchaseGame.getGameName());
                    PlatformGame gamePlatform = game.getGamePlatforms().stream()
                            .filter(gPlatform -> gPlatform.getPlatform().getName().equals(purchaseGame.getGamePlatform()))
                            .findFirst()
                            .orElse(PlatformGame.builder()
                                    .game(Fixtures.getSomeGame1())
                                    .price(BigDecimal.TEN)
                                    .platform(Fixtures.getSomePlatform1())
                                    .build());
                    return GamePurchase.builder()
                            .game(game.getName())
                            .gameImage(game.getMainPhoto())
                            .quantity(purchaseGame.getAmount())
                            .platform(gamePlatform.getPlatform().getName())
                            .price(gamePlatform.getPrice())
                            .build();
                }).toList();
    }


    protected Purchase buildPurchase(PurchaseRequest purchaseRequest) {
        DeliveryType deliveryType = purchaseRequest.getDeliveryType();

        Purchase purchase = Purchase.builder()
                .purchaseNumber(UUID.randomUUID().toString())
                .dateStarted(OffsetDateTime.now())
                .deliveryType(deliveryType)
                .status(OrderStatus.PENDING)
                .build();

        if (deliveryType.equals(DeliveryType.ELECTRONIC_SHIPMENT)) {
            return purchase.withEmailToSend(purchaseRequest.getDeliveryEmail());
        } else if (deliveryType.equals(DeliveryType.PICKUP_POINT)) {
            return purchase.withPickupPointName(purchaseRequest.getPickupPointName());
        } else return purchase.withAddress(Address.builder()
                .postalCode(purchaseRequest.getCustomerAddressPostalCode())
                .city(purchaseRequest.getCustomerAddressCity())
                .address(purchaseRequest.getCustomerAddressStreet())
                .build());
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public List<Purchase> getRecentPurchase() {
        return purchaseRepository.getPurchasesSortDESC();
    }
}
