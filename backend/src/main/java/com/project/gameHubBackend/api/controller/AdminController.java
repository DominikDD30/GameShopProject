package com.project.gameHubBackend.api.controller;

import com.project.gameHubBackend.api.dto.IncomesDTO;
import com.project.gameHubBackend.api.dto.Tuple;
import com.project.gameHubBackend.api.dto.mapper.IncomeMapper;
import com.project.gameHubBackend.business.AnalyticService;
import com.project.gameHubBackend.business.GameService;
import com.project.gameHubBackend.domain.TimePeriod;
import com.project.gameHubBackend.util.DatePair;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.project.gameHubBackend.api.controller.AdminController.ADMIN;


@RestController
@AllArgsConstructor
@RequestMapping(ADMIN)
public class AdminController {
    public static final String ADMIN="/admin";

    private AnalyticService analyticService;
    private GameService gameService;
    private IncomeMapper incomeMapper;

    @GetMapping("/income")
    public IncomesDTO getTotalIncome(
            @RequestParam(value = "dateFrom")LocalDate dateFrom,
            @RequestParam(value = "dateTo") LocalDate dateTo,
            @RequestParam(value = "timePeriod")TimePeriod timePeriod)
    {
        Map<DatePair, BigDecimal> incomes = analyticService.getTotalIncome(dateFrom, dateTo, timePeriod);
        BigDecimal totalIncome = incomes.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        var bestDateAmount=incomes.values().stream().max(BigDecimal::compareTo).get();
        DatePair bestDate = incomes.entrySet().stream().filter(entry -> entry.getValue().equals(bestDateAmount)).findFirst().get().getKey();
        return incomeMapper.mapToIncomesDto(incomes,totalIncome,bestDate,bestDateAmount);
    }

    @GetMapping("/income/categories")
    public List<Tuple<String,BigDecimal>> getTotalIncomeForEachCategory(
            @RequestParam(value = "dateFrom")LocalDate dateFrom,
            @RequestParam(value = "dateTo") LocalDate dateTo)
    {
        return incomeMapper.mapCategoriesIncome(analyticService.getTotalIncomeForEachCategory(dateFrom,dateTo));
    }

    @PatchMapping("/games/{gameId}")
    public void deactivateGame(@PathVariable(value = "gameId")Integer gameId)
    {
        gameService.deactivateGame(gameId);
    }
}
