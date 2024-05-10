package com.project.gameHubBackend.util;

import com.project.gameHubBackend.domain.DeliveryType;
import com.project.gameHubBackend.domain.OrderStatus;
import com.project.gameHubBackend.infrastructure.database.entity.*;
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
public class EntityFixturesT {

    public CategoryEntity getSomeCategory1() {
        return CategoryEntity.builder()
                .categoryName("action")
                .backgroundUrl("somersuastdg2535")
                .build();
    }

    public CategoryEntity getSomeCategory2() {
        return CategoryEntity.builder()
                .categoryName("strategy")
                .backgroundUrl("sdgsdadsha")
                .build();
    }

    public CategoryEntity getSomeCategory3() {
        return CategoryEntity.builder()
                .categoryName("adventure")
                .backgroundUrl("sadgahsaijvnsa578")
                .build();
    }

    public OpinionCustomerGameEntity getSomeOpinion1() {
        return OpinionCustomerGameEntity.builder()
                .date(OffsetDateTime.of(2022,5,1,5,0,0,0,ZoneOffset.UTC))
                .description("good game")
                .stars((short) 4)
                .game(getSomeGame1())
                .customer(getSomeCustomer1())
                .build();
    }

    public PlatformEntity getSomePlatform1() {
        return PlatformEntity.builder()
                .name("PC")
                .build();
    }

    public PlatformEntity getSomePlatform2() {
        return PlatformEntity.builder()
                .name("Nintendo")
                .build();
    }

    public PlatformEntity getSomePlatform3() {
        return PlatformEntity.builder()
                .name("XBOX")
                .build();
    }

    public GameEntity getSomeGame1() {
        GameEntity gameEntity = GameEntity.builder()
                .name("gta")
                .gameNumber("13985290371")
                .description("gta description")
                .mainPhoto("asgagdbsb3564")
                .isSoldOut(false)
                .isContinuouslyDelivered(true)
                .trailer(TrailerEntity.builder().url("agagss").previewImage("asihwnv").build())
                .build();

        Set<PhotoEntity> photoEntitySet = getSomePhotosEntity1();
        Set<PublisherGameEntity> publishers = Set.of(new PublisherGameEntity(1, new PublisherEntity(1, "Rockstar", null), gameEntity));
        Set<CategoryGameEntity> categoryGameEntitySet = Set.of(getSomeCategoryGameEntity1(), getSomeCategoryGameEntity2());
        Set<PlatformGameEntity> platformGameEntitySet = Set.of(getSomePlatformGameEntity1(), getSomePlatformGameEntity2());
        photoEntitySet.forEach(photo -> photo.setGame(gameEntity));
        categoryGameEntitySet.forEach(categoryGame -> categoryGame.setGame(gameEntity));
        platformGameEntitySet.forEach(platformGame -> platformGame.setGame(gameEntity));

        gameEntity.setPhotos(photoEntitySet);
        gameEntity.setPublishers(publishers);
        gameEntity.setGameCategories(categoryGameEntitySet);
        gameEntity.setGamePlatforms(platformGameEntitySet);
        return gameEntity;
    }

    public GameEntity getSomeGame2() {
        GameEntity gameEntity = GameEntity.builder()
                .name("witcher")
                .gameNumber("203892-1-1")
                .description("witcher description")
                .mainPhoto("imasdgashs2636")
                .isSoldOut(false)
                .isContinuouslyDelivered(true)
                .trailer(TrailerEntity.builder().url("paskgoa").previewImage("oaiwfeiw").build())
                .build();

        Set<PhotoEntity> photoEntitySet = getSomePhotosEntity2();
        Set<PublisherGameEntity> publishers = Set.of(new PublisherGameEntity(1, new PublisherEntity(1, "CD Red Studio", null), gameEntity));
        Set<CategoryGameEntity> categoryGameEntitySet = Set.of(getSomeCategoryGameEntity2());
        Set<PlatformGameEntity> platformGameEntitySet = Set.of(getSomePlatformGameEntity1());
        photoEntitySet.forEach(photo -> photo.setGame(gameEntity));
        categoryGameEntitySet.forEach(categoryGame -> categoryGame.setGame(gameEntity));
        platformGameEntitySet.forEach(platformGame -> platformGame.setGame(gameEntity));

        gameEntity.setPhotos(photoEntitySet);
        gameEntity.setPublishers(publishers);
        gameEntity.setGameCategories(categoryGameEntitySet);
        gameEntity.setGamePlatforms(platformGameEntitySet);
        return gameEntity;
    }

    @NotNull
    private Set<PhotoEntity> getSomePhotosEntity1() {
        return Set.of(
                PhotoEntity.builder().photoId(1).url("somePhoatwi").build(),
                PhotoEntity.builder().photoId(2).url("owghiawl").build());
    }

    @NotNull
    private Set<PhotoEntity> getSomePhotosEntity2() {
        return Set.of(PhotoEntity.builder().photoId(3).url("asegwaatehna").build());
    }

    private CategoryGameEntity getSomeCategoryGameEntity1() {
        return CategoryGameEntity.builder()
                .categoryGameId(1)
                .category(getSomeCategory1()).build();
    }

    private CategoryGameEntity getSomeCategoryGameEntity2() {
        return CategoryGameEntity.builder()
                .categoryGameId(2)
                .category(getSomeCategory2()).build();
    }


    public PlatformGameEntity getSomePlatformGameEntity1() {
        PlatformEntity somePlatform1 = getSomePlatform1();
        somePlatform1.setPlatformId(1);
        return PlatformGameEntity.builder()
                .platformGameId(1)
                .platform(somePlatform1).leftInStock(3).price(BigDecimal.TEN).build();
    }

    public PlatformGameEntity getSomePlatformGameEntity2() {
        return PlatformGameEntity.builder().platformGameId(2)
                .platform(getSomePlatform2()).leftInStock(1).price(BigDecimal.valueOf(23)).build();
    }

    public CustomerEntity getSomeCustomer1() {
        return CustomerEntity.builder()
                .name("Adam")
                .surname("Nowak")
                .email("AdamNowak14@o2.pl")
                .build();
    }

    public PurchaseEntity getSomePurchase1() {
        return
                PurchaseEntity.builder()
                        .purchaseNumber("2352sgdhhs")
                        .dateStarted(OffsetDateTime.of(LocalDate.of(2023, 11, 17), LocalTime.of(10, 30), ZoneOffset.UTC))
                        .status(OrderStatus.PENDING)
                        .deliveryType(DeliveryType.ELECTRONIC_SHIPMENT)
                        .customer(getSomeCustomer1())
                        .gamePurchases(List.of(getSomeGamePurchase1()))
                        .emailToSend("AdamNowak14@o2.pl")
                        .build();
    }

    public PurchaseEntity getSomePurchase2() {
        return
                PurchaseEntity.builder()
                        .purchaseNumber("ajifdudhsgj92")
                        .dateStarted(OffsetDateTime.of(LocalDate.of(2023, 11, 17), LocalTime.of(10, 30), ZoneOffset.UTC))
                        .status(OrderStatus.PENDING)
                        .deliveryType(DeliveryType.PICKUP_POINT)
                        .customer(getSomeCustomer1())
                        .gamePurchases(List.of(getSomeGamePurchase1()))
                        .pickupPointName("36DF")
                        .build();
    }

    public PurchaseEntity getSomePurchase3() {
        return
                PurchaseEntity.builder()
                        .purchaseNumber("sadgiwio2521")
                        .dateStarted(OffsetDateTime.of(LocalDate.of(2023, 11, 18), LocalTime.of(10, 0), ZoneOffset.UTC))
                        .status(OrderStatus.PENDING)
                        .deliveryType(DeliveryType.COURIER)
                        .customer(getSomeCustomer1())
                        .address(AddressEntity.builder()
                                .postalCode("45-259")
                                .city("Warsaw")
                                .address("piekna14a")
                                .build())
                        .build();
    }


    public GamePurchaseEntity getSomeGamePurchase1() {
        return GamePurchaseEntity.builder()
                .game("gta")
                .gameImage("someGameImage.png")
                .quantity(3)
                .platform("Steam Key")
                .price(BigDecimal.valueOf(32))
                .build();
    }
}
