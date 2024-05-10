package com.project.gameHubBackend.business;
import com.project.gameHubBackend.business.purchaseService.PurchaseService;
import com.project.gameHubBackend.domain.DeliveryType;
import com.project.gameHubBackend.domain.Purchase;
import com.project.gameHubBackend.domain.PurchaseRequest;
import com.project.gameHubBackend.domain.exception.InvalidInputDataException;
import com.project.gameHubBackend.infrastructure.database.repository.PurchaseRepository;
import com.project.gameHubBackend.util.FixturesT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {

    @Mock
    CustomerService customerService;
    @Mock
    GameService gameService;

    @Mock
    PurchaseRepository purchaseRepository;
    @InjectMocks
    private PurchaseService purchaseService;

    @Test
    void thatGetPurchasesForCustomerWorkCorrectly(){
        //given
        String someCustomerEmail="JohnDoe@email.com";
        Mockito.when(purchaseRepository.getPurchasesForCustomer(any(String.class)))
                        .thenReturn(List.of(FixturesT.getSomePurchase1(),FixturesT.getSomePurchase2()));

        //when
        List<Purchase> result = purchaseService.getPurchasesForCustomer(someCustomerEmail);
        //then
        Assertions.assertThat(result).hasSize(2);
    }

    @Test
    void thatMakePurchaseForTheFirstTimeWorkCorrectly() {
        //given
        PurchaseRequest somePurchaseRequest1 = FixturesT.getSomePurchaseRequest1();

        Mockito.when(customerService.getCustomerByEmail(somePurchaseRequest1.getCustomerEmail()))
                .thenReturn(Optional.empty());

        Mockito.when(gameService.getGameByName(any(String.class)))
                .thenReturn(FixturesT.getSomeGame2());
        //when
        Purchase purchase = purchaseService.makePurchase(somePurchaseRequest1);
        //then
        Assertions.assertThat(purchase).isNotNull();
    }


    @Test
    void thatMakePurchaseForNextTimeWorkCorrectly() {
        //given
        PurchaseRequest somePurchaseRequest1 = FixturesT.getSomePurchaseRequest1();

        Mockito.when(customerService.getCustomerByEmail(somePurchaseRequest1.getCustomerEmail()))
                .thenReturn(Optional.of(FixturesT.getSomeCustomer1()));

        Mockito.when(gameService.getGameByName(any(String.class)))
                .thenReturn(FixturesT.getSomeGame2());
        //when
        Purchase purchase = purchaseService.makePurchase(somePurchaseRequest1);
        //then
        Assertions.assertThat(purchase).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPurchaseRequest")
    void thatMakePurchaseFailWhenInvalidDataProvided(PurchaseRequest someRequest,String errorMessage) {
        //given when then
        InvalidInputDataException exception = org.junit.jupiter.api.Assertions.assertThrows(
                InvalidInputDataException.class, () -> purchaseService.makePurchase(someRequest));

        Assertions.assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    private static Stream<Arguments>provideInvalidPurchaseRequest(){
        PurchaseRequest somePurchaseRequest = FixturesT.getSomePurchaseRequest1();
        String addressErrorMessage = "address data was null for chosen Postal/Courier Delivery";
        String noPickupProviderMessage = "pickup point name was null for chosen PickupPoint Delivery";
        return Stream.of(
                Arguments.of(somePurchaseRequest.withDeliveryType(DeliveryType.COURIER).withCustomerAddressCity(""),
                        addressErrorMessage),
                Arguments.of(somePurchaseRequest.withDeliveryType(DeliveryType.COURIER).withCustomerAddressStreet(""),
                        addressErrorMessage),
                Arguments.of(somePurchaseRequest.withDeliveryType(DeliveryType.COURIER).withCustomerAddressPostalCode(""),
                        addressErrorMessage),
                Arguments.of(somePurchaseRequest.withDeliveryType(DeliveryType.POSTAL).withCustomerAddressPostalCode(""),
                        addressErrorMessage),
                Arguments.of(somePurchaseRequest.withDeliveryType(DeliveryType.PICKUP_POINT).withPickupPointName(""),
                        noPickupProviderMessage)
        );
    }

}