package com.project.gameHubBackend.business;

import com.project.gameHubBackend.domain.TimePeriod;
import com.project.gameHubBackend.util.DatePair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
@AllArgsConstructor
public class AnalyticService {

    private IncomeService incomeService;

    public Map<DatePair, BigDecimal> getTotalIncome(LocalDate dateFrom, LocalDate dateTo, TimePeriod timePeriod) {
    return incomeService.getTotalIncome(dateFrom,dateTo,timePeriod);
    }

    public Map<String, BigDecimal> getTotalIncomeForEachCategory(LocalDate dateFrom, LocalDate dateTo) {
        return incomeService.getTotalIncomeForEachCategory(dateFrom,dateTo);
    }



}
