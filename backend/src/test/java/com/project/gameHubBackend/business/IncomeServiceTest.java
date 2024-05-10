package com.project.gameHubBackend.business;

import com.project.gameHubBackend.domain.*;
import com.project.gameHubBackend.infrastructure.database.repository.CategoryRepository;
import com.project.gameHubBackend.infrastructure.database.repository.GameRepository;
import com.project.gameHubBackend.infrastructure.database.repository.PurchaseRepository;
import com.project.gameHubBackend.util.DatePair;
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

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.project.gameHubBackend.util.FixturesT.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncomeServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private IncomeService incomeService;

    @ParameterizedTest
    @MethodSource("provideDifferentTimePeriod")
    void getTotalIncome(TimePeriod timePeriod) {
        //given
        LocalDate someDateFrom = getSomeLocalDate1();
        LocalDate someDateTo = getSomeLocalDate1().withYear(2022).withMonth(7);
        BigDecimal someIncome = BigDecimal.valueOf(1260);
        when(purchaseRepository.getTotalIncome(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(someIncome);
        //when
        Map<DatePair, BigDecimal> result = incomeService.getTotalIncome(someDateFrom, someDateTo, timePeriod);
        //then
        Assertions.assertThat(result)
                .hasSize((int) ChronoUnit.valueOf(timePeriod.name()).between(someDateFrom,someDateTo)+1);

        Assertions.assertThat(result.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add))
                .isEqualTo(someIncome.multiply(BigDecimal.valueOf(result.size())));

        Assertions.assertThat(result.keySet()).allMatch(datePair ->
                        Period.between(datePair.getFrom(),datePair.getTo())
                                .get(ChronoUnit.valueOf(timePeriod.name())) <= 1);
    }
    private static Stream<Arguments> provideDifferentTimePeriod() {
        return Stream.of(
                Arguments.of(TimePeriod.YEARS),
                Arguments.of(TimePeriod.MONTHS),
                Arguments.of(TimePeriod.DAYS)
        );
    }


    @Test
    void getTotalIncomeForEachCategory() {
        //given
        LocalDate someDateFrom = getSomeLocalDate1();
        LocalDate someDateTo = getSomeLocalDate1().withYear(2022).withMonth(7);
        List<Category> someCategories = List.of(getSomeCategory1(), getSomeCategory2(), getSomeCategory3());
        List<Purchase> somePurchases = List.of(
                getSomePurchase1().withDateCompleted(
                        OffsetDateTime.of(someDateFrom.withMonth(9), LocalTime.of(10,0),ZoneOffset.UTC)),
                getSomePurchase2().withDateCompleted(
                        OffsetDateTime.of(someDateFrom.withMonth(8), LocalTime.of(10,0),ZoneOffset.UTC)));


        when(purchaseRepository.getPurchasesFromPeriod(any(LocalDate.class),any(LocalDate.class)))
                .thenReturn(somePurchases);
        when(categoryRepository.getCategories())
                .thenReturn(someCategories);
        when(gameRepository.getByName(any(String.class)))
                .thenReturn(getSomeGame2())
                .thenReturn(getSomeGame1());

        //when
        Map<String, BigDecimal> result = incomeService.getTotalIncomeForEachCategory(someDateFrom, someDateTo);
        //then
        Assertions.assertThat(result).hasSize(someCategories.size());
        Assertions.assertThat(result.get(getSomeCategory1().getCategoryName())).isEqualTo(BigDecimal.valueOf(50));
        Assertions.assertThat(result.get(getSomeCategory2().getCategoryName())).isEqualTo(BigDecimal.valueOf(90));
        Assertions.assertThat(result.get(getSomeCategory3().getCategoryName())).isEqualTo(BigDecimal.valueOf(0));
    }
}