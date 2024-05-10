package com.project.gameHubBackend.util;

import com.project.gameHubBackend.api.dto.OpinionDTO;
import com.project.gameHubBackend.domain.*;
import com.project.gameHubBackend.infrastructure.database.entity.PublisherEntity;
import com.project.gameHubBackend.infrastructure.database.entity.PublisherGameEntity;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;


@UtilityClass
public class FixturesT {


    public Game getSomeGame1() {
        Game game = Game.builder()
                .name("gta")
                .gameNumber("13985290371")
                .description("gta description")
                .mainPhoto("asgagdbsb3564")
                .isContinuouslyDelivered(true)
                .isSoldOut(false)
                .trailer(Trailer.builder().url("agagss").previewImage("asihwnv").build())
                .photos(getSomePhotos1())
                .publishers(Set.of(PublisherGame.builder()
                        .publisherGameId(1)
                        .publisher(Publisher.builder().publisherId(1).publisherName("Rockstar").build())
                        .build()))
                .gameCategories(Set.of(getSomeCategoryGame1(), getSomeCategoryGame2()))
                .gamePlatforms(Set.of(getSomePlatformGame2(), getSomePlatformGame3()))
                .build();

//        List<Photo> photoList = getSomePhotos1().stream().map(photo -> photo.withGame(game)).toList();
//        List<CategoryGame> categoryGameList = Stream.of(getSomeCategoryGame1(),getSomeCategoryGame2())
//                .map(categoryGame -> CategoryGame.builder().category(categoryGame.getCategory()).game(game).build()).toList();
//        List<PlatformGame> platformGameList = Stream.of(getSomePlatformGame1(),getSomePlatformGame2())
//                .map(platformGame -> PlatformGame.builder().platform(platformGame.getPlatform()).game(game).build()).toList();

//        return game.withPhotos(photoList).withGameCategories(categoryGameList).withGamePlatforms(platformGameList);
        return game;
    }

    public Game getSomeGame2() {
        Game game = Game.builder()
                .name("witcher")
                .gameNumber("203892-1-1")
                .description("witcher description")
                .mainPhoto("imasdgashs2636")
                .isSoldOut(false)
                .isContinuouslyDelivered(true)
                .trailer(Trailer.builder().url("paskgoa").previewImage("oaiwfeiw").build())
                .photos(getSomePhotos2())
                .publishers(Set.of(PublisherGame.builder()
                        .publisherGameId(1)
                        .publisher(Publisher.builder().publisherId(1).publisherName("CD Red Studio").build())
                        .build()))
                .gameCategories(Set.of(getSomeCategoryGame2()))
                .gamePlatforms(Set.of(getSomePlatformGame1()))
                .build();

//        List<Photo> photoList = getSomePhotos2().stream().map(photo -> photo.withGame(game)).toList();
//        List<CategoryGame> categoryGameList = Stream.of(getSomeCategoryGame2())
//                .map(categoryGame -> CategoryGame.builder().category(categoryGame.getCategory()).game(game).build()).toList();
//        List<PlatformGame> platformGameList = Stream.of(getSomePlatformGame1())
//                .map(platformGame -> PlatformGame.builder().platform(platformGame.getPlatform()).game(game).build()).toList();
        return game;
//        return game.withPhotos(photoList).withGameCategories(categoryGameList).withGamePlatforms(platformGameList);
    }


    @NotNull
    private Set<Photo> getSomePhotos1() {
        return Set.of(
                Photo.builder().url("somePhoatwi").build(),
                Photo.builder().url("owghiawl").build());
    }

    @NotNull
    private Set<Photo> getSomePhotos2() {
        return Set.of(Photo.builder().url("asegwaatehna").build());
    }

    private CategoryGame getSomeCategoryGame1() {
        return CategoryGame.builder().categoryGameId(1).category(getSomeCategory1()).build();
    }

    private CategoryGame getSomeCategoryGame2() {
        return CategoryGame.builder().categoryGameId(2).category(getSomeCategory2()).build();
    }


    private PlatformGame getSomePlatformGame1() {
        return PlatformGame.builder().platform(getSomePlatform1()).leftInStock(3).price(new BigDecimal("10.00")).build();
    }

    private PlatformGame getSomePlatformGame2() {
        return PlatformGame.builder().platform(getSomePlatform1()).leftInStock(1).price(new BigDecimal("23.00")).build();
    }

    private PlatformGame getSomePlatformGame3() {
        return PlatformGame.builder().platform(getSomePlatform2()).leftInStock(1).price(new BigDecimal("20.00")).build();
    }


    public Category getSomeCategory1() {
        return Category.builder()
                .categoryId(1)
                .categoryName("Action")
                .backgroundUrl("somersuastdg2535")
                .build();
    }

    public Category getSomeCategory2() {
        return Category.builder()
                .categoryId(2)
                .categoryName("Strategy")
                .backgroundUrl("sdgsdadsha")
                .build();
    }

    public Category getSomeCategory3() {
        return Category.builder()
                .categoryId(3)
                .categoryName("adventure")
                .backgroundUrl("sadgahsaijvnsa578")
                .build();
    }

    public Platform getSomePlatform1() {
        return Platform.builder()
                .platformId(1)
                .name("PC")
                .build();
    }

    public Platform getSomePlatform2() {
        return Platform.builder()
                .platformId(2)
                .name("PS")
                .build();
    }

    public Platform getSomePlatform3() {
        return Platform.builder()
                .platformId(3)
                .name("XBOX")
                .build();
    }


    public Purchase getSomePurchase1() {
        return
                Purchase.builder()
                        .purchaseId(14)
                        .purchaseNumber("ajifdudhsgj92")
                        .dateStarted(OffsetDateTime.of(LocalDate.of(2023, 11, 18), LocalTime.of(10, 0), ZoneOffset.UTC))
                        .status(OrderStatus.PENDING_SHIPMENT)
                        .deliveryType(DeliveryType.POSTAL)
                        .gamePurchases(List.of(
                                GamePurchase.builder().game("witcher").platform("PC").quantity(1).price(BigDecimal.valueOf(40)).build()
                        ))
                        .address(Address.builder()
                                .postalCode("45-259")
                                .city("Warsaw")
                                .address("piekna14a")
                                .build())
                        .build();
    }

    public Purchase getSomePurchase2() {
        return
                Purchase.builder()
                        .purchaseId(14)
                        .purchaseNumber("ajifdudhsgj92")
                        .dateStarted(OffsetDateTime.of(LocalDate.of(2023, 12, 20), LocalTime.of(11, 0), ZoneOffset.UTC))
                        .status(OrderStatus.PENDING_SHIPMENT)
                        .gamePurchases(List.of(
                                GamePurchase.builder().game("gta").platform("XBOX").quantity(2).price(BigDecimal.valueOf(25)).build()
                        ))
                        .pickupPointName("DFQ46H")
                        .build();
    }

    public PurchaseRequest getSomePurchaseRequest1() {
        return PurchaseRequest.builder()
                .customerName("Adam")
                .customerSurname("Nowak")
                .customerEmail("AdamNowak14@o2.pl")
                .deliveryType(DeliveryType.COURIER)
                .games(List.of(
                        DesiredGame.builder().gameName("witcher").gamePlatform("PC").amount(1).build())
                )
                .customerAddressCity("Warsaw")
                .customerAddressPostalCode("45-259")
                .customerAddressStreet("piekna14")
                .build();
    }

    public Customer getSomeCustomer1() {
        return Customer.builder()
                .name("Adam")
                .surname("Nowak")
                .email("AdamNowak14@o2.pl")
                .build();
    }


    public static LocalDate getSomeLocalDate1() {
        return LocalDate.from(LocalDate.of(2013, 5, 1));
    }


    public static OpinionCustomerGame getSomeOpinion1() {
       return OpinionCustomerGame.builder()
                .date(OffsetDateTime.of(2022,5,1,5,0,0,0,ZoneOffset.UTC))
                .game(getSomeGame1())
                .customer(getSomeCustomer1())
                .stars((short) 4)
                .description("good game")
                .build();
    }
}
