package com.project.gameHubBackend.util;

import com.project.gameHubBackend.api.dto.*;
import com.project.gameHubBackend.domain.DeliveryType;
import com.project.gameHubBackend.domain.OrderStatus;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;


@UtilityClass
public class DTOFixturesT {


    public CategoryDTO getSomeCategoryDTO1() {
        return CategoryDTO.builder()
                .categoryName("action")
                .backgroundUrl("somersuastdg2535")
                .build();
    }

    public CategoryDTO getSomeCategoryDTO2() {
        return CategoryDTO.builder()
                .categoryName("strategy")
                .backgroundUrl("sdgsdadsha")
                .build();
    }

    public CategoryDTO getSomeCategoryDTO3() {
        return CategoryDTO.builder()
                .categoryName("adventure")
                .backgroundUrl("sadgahsaijvnsa578")
                .build();
    }

    public PlatformDTO getSomePlatformDTO1() {
        return PlatformDTO.builder()
                .name( "PC")
                .build();
    }

    public PlatformDTO getSomePlatformDTO2() {
        return PlatformDTO.builder()
                .name( "PSP")
                .build();
    }

    public PlatformDTO getSomePlatformDTO3() {
        return PlatformDTO.builder()
                .name( "XBOX")
                .build();
    }

    public GameDTO getSomeGameDTO1(){
        return GameDTO.builder()
                .name("gta")
                .mainPhoto("asgagdbsb3564")
                .build();
    }

    public GameDTO getSomeGameDTO2(){
        return GameDTO.builder()
                .name("witcher")
                .mainPhoto("imasdgashs2636")
                .build();
    }

//    public PurchaseRequestDTO getSomePurchaseRequestDTO1() {
//        return PurchaseRequestDTO.builder()
//                .customerName("Adam")
//                .customerSurname("Nowak")
//                .customerEmail("AdamNowak14@o2.pl")
//                .deliveryType("PICKUP_POINT")
//                .games(
//                    List.of(
//                  DesiredGameDTO.builder().gameName("witcher").gamePlatform("XBOX").amount(1).build(),
//                        DesiredGameDTO.builder().gameName("gta").gamePlatform("PS").amount(2).build()
//               )))
//                .pickupPointName("45DEF")
//                .build();
//    }

    public  PurchaseRequestDTO getSomePurchaseRequestDTO1() {
        return PurchaseRequestDTO.builder()
                .customerName("Adam")
                .customerSurname("Nowak")
                .customerEmail("AdamNowak14@o2.pl")
                .deliveryType("COURIER")
                .games(List.of(
                        DesiredGameDTO.builder().gameName("witcher").gamePlatform("PC").amount(1).build())
                )
                .customerAddressCity("Warsaw")
                .customerAddressPostalCode("45-259")
                .customerAddressStreet("piekna14")
                .build();
    }
//
//    public  PurchaseRequestDTO getSomePurchaseRequestDTO3() {
//        return PurchaseRequestDTO.builder()
//                .customerName("Tomasz")
//                .customerSurname("Kowalski")
//                .customerEmail("kowalski2g@gmail.com")
//                .deliveryType("ELECTRONIC_SHIPMENT")
//                .games(List.of(DesiredGameDTO.builder().gameName("gta").gamePlatform("PSP").amount(1).build()))
//                .build();
//    }


    // TODO: 02.03.2024
    public InvoiceDTO getSomeInvoiceDTO1() {
        return InvoiceDTO.builder()
                .purchaseNumber("ajifdudhsgj92")
//                .dateStarted(OffsetDateTime.of(LocalDate.of(2023, 11, 18), LocalTime.of(10, 0), ZoneOffset.UTC).toString())
//                .gamePurchases(List.of(
//                GamePurchaseDTO.builder().game("witcher").platform("PC").quantity(1).price(BigDecimal.valueOf(40)).build()
//                ))
//                .totalPrice(BigDecimal.valueOf(40))
                .build();
    }

    public PurchaseDTO getSomePurchaseDTO1() {
        return PurchaseDTO.builder()
                .purchaseNumber("ajifdudhsgj92")
                .status(OrderStatus.DELIVERED.name())
                .deliveryType(DeliveryType.POSTAL.name())
                .dateStarted(OffsetDateTime.of(LocalDate.of(2023, 11, 18), LocalTime.of(10, 0), ZoneOffset.UTC).toString())
                .dateCompleted(OffsetDateTime.of(LocalDate.of(2023, 11, 21), LocalTime.of(13, 35), ZoneOffset.UTC).toString())
                .gamePurchases(List.of(
                        GamePurchaseDTO.builder().game("witcher").platform("PC").quantity(1).price(BigDecimal.valueOf(40))
                                .opinion(getSomeOpinionDTO()).build()
                ))
                .postalCode("45-259")
                .city("Warsaw")
                .address("piekna14a")
                .totalPrice(BigDecimal.valueOf(40))
                .build();
    }

    public static OpinionDTO getSomeOpinionDTO() {
       return OpinionDTO.builder()
                .date("2022-02-01")
                .stars((short) 4)
                .description("good game")
                .build();
    }

    public static OpinionRequestDTO getSomeOpinionRequestDTO1() {
        return OpinionRequestDTO.builder()
                .gameName("witcher")
                .customerEmail("AdamNowak14@o2.pl")
                .stars((short) 5)
                .description("great game")
                .build();
    }
}
