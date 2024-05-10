package com.project.gameHubBackend.business;

import com.project.gameHubBackend.domain.*;
import com.project.gameHubBackend.infrastructure.database.repository.CategoryRepository;
import com.project.gameHubBackend.infrastructure.database.repository.GameRepository;
import com.project.gameHubBackend.infrastructure.database.repository.PurchaseRepository;
import com.project.gameHubBackend.util.DatePair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IncomeService {
    private PurchaseRepository purchaseRepository;
    private CategoryRepository categoryRepository;
    private GameRepository gameRepository;



    public Map<DatePair, BigDecimal> getTotalIncome(LocalDate dateFrom, LocalDate dateTo, TimePeriod timePeriod) {
        if (dateTo.isAfter(LocalDate.now())) {
            throw new RuntimeException();
        }
        return calculate(dateFrom, dateTo, timePeriod);
    }

    public Map<String, BigDecimal> getTotalIncomeForEachCategory(LocalDate dateFrom, LocalDate dateTo) {

        if (dateTo.isAfter(LocalDate.now())) {
            throw new RuntimeException();
        }
        List<Purchase> purchases = purchaseRepository.getPurchasesFromPeriod(dateFrom, dateTo);

        List<Category> categories = categoryRepository.getCategories();

        return categories.stream().collect(Collectors.toMap(
                Category::getCategoryName,
                category -> getPurchasesForCategory(category.getCategoryName(),purchases,dateFrom,dateTo)));
    }

    private BigDecimal getPurchasesForCategory(String categoryName, List<Purchase> purchases, LocalDate dateFrom, LocalDate dateTo) {
        return purchases.stream()
                .filter(purchase->purchase.getDateCompleted().isAfter(OffsetDateTime.of(dateFrom, LocalTime.now(), ZoneOffset.UTC))
                        && purchase.getDateCompleted().isBefore(OffsetDateTime.of(dateTo, LocalTime.now(), ZoneOffset.UTC)))
                .flatMap(_purchase -> _purchase.getGamePurchases().stream()
                        .filter(gamePurchase -> containsCategory(categoryName, gamePurchase)))
                .reduce(BigDecimal.ZERO,
                        (ac, next) -> ac.add(next.getPrice().multiply(BigDecimal.valueOf(next.getQuantity()))),
                        BigDecimal::add);
    }

    private boolean containsCategory(String categoryName, GamePurchase gamePurchase) {
        Game game = gameRepository.getByName(gamePurchase.getGame());
        Set<String> categoryNames = game.getGameCategories().stream()
                .map(categoryGame -> categoryGame.getCategory().getCategoryName()).collect(Collectors.toSet());
        return categoryNames.contains(categoryName);
    }


    private Map<DatePair, BigDecimal> calculate(LocalDate dateFrom, LocalDate dateTo, TimePeriod timePeriod) {
        Map<DatePair, BigDecimal> result = new LinkedHashMap<>();
        long timeDurance = ChronoUnit.valueOf(timePeriod.name()).between(dateFrom, dateTo);

        LocalDate currentFrom = dateFrom;
        LocalDate currentTo;
        for (int i = 0; i <= timeDurance; i++) {
            currentTo = currentFrom.plus(1, ChronoUnit.valueOf(timePeriod.name()));
            BigDecimal income;

            if (i != timeDurance) {
                income = calculateIncome(currentFrom, currentTo);
                result.put(new DatePair(currentFrom, currentTo), income);
                currentFrom = currentTo;
            } else {
                income = calculateLastIncome(currentFrom, dateTo);
                result.put(new DatePair(currentFrom, dateTo), income);
            }
        }
        return result;
    }

    private BigDecimal calculateIncome(LocalDate currentFrom, LocalDate currentTo) {
        return Optional.ofNullable(purchaseRepository.getTotalIncome(currentFrom, currentTo)).orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculateLastIncome(LocalDate currentFrom, LocalDate dateTo) {
        return Optional.ofNullable(purchaseRepository.getTotalIncome(currentFrom, dateTo)).orElse(BigDecimal.ZERO);
    }
}
