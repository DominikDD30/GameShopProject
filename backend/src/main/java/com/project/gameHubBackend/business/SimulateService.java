package com.project.gameHubBackend.business;

import com.github.javafaker.Faker;
import com.project.gameHubBackend.api.dto.OpinionRequestDTO;
import com.project.gameHubBackend.business.purchaseService.PurchaseServiceForSimulation;
import com.project.gameHubBackend.domain.*;
import com.project.gameHubBackend.infrastructure.database.entity.GameEntity;
import com.project.gameHubBackend.infrastructure.database.entity.PlatformGameEntity;
import com.project.gameHubBackend.util.Functions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class SimulateService {
    private PurchaseServiceForSimulation simulationPurchaseService;
    private GameService gameService;

    private OpinionService opinionService;
    private static final Faker faker=new Faker();
     static final List<List<String>> customer =generateCustomers();
    static final List<String> PickupPoints = generatePickupPoints();
    static final List<List<String>> addressCityAndPostalCode = generateCitiesWithPostalCode();

    static final List<String> addressStreet = generateAddressStreets();

    private static List<String> generateAddressStreets() {
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(faker.address().streetAddress());
        }
        return list;
    }

    private static List<List<String>> generateCitiesWithPostalCode() {
        List<List<String>> list=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String city=faker.address().city();
            String postalCode=faker.address().zipCode();
            list.add(List.of(city,postalCode));
        }
        return list;
    }


    private static List<String> generatePickupPoints() {
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(faker.address().streetPrefix()+faker.number().numberBetween(10,99)+faker.lorem().character());
        }
        return list;
    }


    private static List<List<String>> generateCustomers() {
        List<List<String>> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String name=faker.name().firstName();
            String surname=faker.name().lastName();
            String email=name.toLowerCase()+surname+"@email.com";
            list.add(List.of(name,surname,email));
        }
        return list;
    }


    public void makePurchases(int amount){
        List<PurchaseRequest> purchaseRequests = generateRandomPurchaseRequests(amount);
        purchaseRequests.forEach((purchaseRequest -> simulationPurchaseService.makePurchase(purchaseRequest)));
        makeOpinions(purchaseRequests);
        simulationPurchaseService.simulatePurchasesDateToCompleted();
    }

    private void makeOpinions(List<PurchaseRequest> purchaseRequests) {
        purchaseRequests.forEach(pr->opinionService.createOpinion(
                OpinionRequestDTO.builder()
                                .gameName(pr.getGames().get(Functions.getRandomNumber(0,pr.getGames().size()-1)).getGameName())
                                .stars(Functions.getRandomNumber(4,5).shortValue())
                                .customerEmail(pr.getCustomerEmail())
                                .description(faker.lorem().sentence(50))
                                .build()));
    }

    private  List<PurchaseRequest> generateRandomPurchaseRequests(int numberOfRequests) {
        List<PurchaseRequest> requests = new ArrayList<>();
        for (int i = 0; i < numberOfRequests; i++) {
            requests.add(generateRequest());
        }
        return requests;
    }

    private  PurchaseRequest generateRequest() {
        List<String> customer = getRandomElement(SimulateService.customer);

        Integer flag = Functions.getRandomNumber(1, 3);
        PurchaseRequest.PurchaseRequestBuilder requestBuilder = switch (flag) {
            case 1 -> generateRequestWithPostalDelivery();
            case 2 -> generateRequestWithElectronicDelivery(customer.get(2));
            case 3 -> generateRequestWithPickupPointDelivery();
            default -> throw new RuntimeException();
        };
        return requestBuilder
                .customerName(customer.get(0))
                .customerSurname(customer.get(1))
                .customerEmail(customer.get(2))
                .games(getRandomGames())
                .build();
    }

    private  List<DesiredGame> getRandomGames() {
        List<DesiredGame> games=new ArrayList<>();
        int limit = Functions.getRandomNumber(1, 3);
        for (int i = 0; i <limit;  i++) {
            List<Game> allGames = gameService.getAllGames();
            Game randomGame = allGames.get(Functions.getRandomNumber(0, allGames.size() - 1));


            List<PlatformGame> gamePlatforms = randomGame.getGamePlatforms().stream().toList();
            games.add(new DesiredGame(
                    randomGame.getName(),
                    getRandomElement(gamePlatforms).getPlatform().getName(),
                    Functions.getRandomNumber(1,3)));
        }
        return games;
    }


    private static PurchaseRequest.PurchaseRequestBuilder generateRequestWithPostalDelivery() {
        List<String> addressAndPostalCode = getRandomElement(addressCityAndPostalCode);
        String street = getRandomElement(addressStreet);
        return PurchaseRequest.builder()
                .deliveryType(DeliveryType.POSTAL)
                .customerAddressCity(addressAndPostalCode.get(0))
                .customerAddressPostalCode(addressAndPostalCode.get(1))
                .customerAddressStreet(street);
    }
    private static PurchaseRequest.PurchaseRequestBuilder generateRequestWithElectronicDelivery(String deliveryEmail) {
        return PurchaseRequest.builder()
                .deliveryType(DeliveryType.ELECTRONIC_SHIPMENT)
                .deliveryEmail(deliveryEmail);
    }
    private static PurchaseRequest.PurchaseRequestBuilder generateRequestWithPickupPointDelivery() {
        return PurchaseRequest.builder()
                .deliveryType(DeliveryType.PICKUP_POINT)
                .pickupPointName(getRandomElement(PickupPoints));
    }

    private static <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
