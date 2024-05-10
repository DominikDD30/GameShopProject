package com.project.gameHubBackend.api.dto.mapper;

import com.project.gameHubBackend.api.dto.IncomeDTO;
import com.project.gameHubBackend.api.dto.IncomesDTO;
import com.project.gameHubBackend.api.dto.Tuple;
import com.project.gameHubBackend.util.DatePair;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface IncomeMapper {


    default IncomesDTO mapToIncomesDto(Map<DatePair, BigDecimal> incomes,BigDecimal totalIncome, DatePair bestDate, BigDecimal bestDateAmount) {
        return new IncomesDTO(
                incomes.entrySet().stream().map(entry -> new IncomeDTO(entry.getKey(), entry.getValue())).toList(),
                totalIncome,
                bestDate,
                bestDateAmount
        );
    }

   default List<Tuple<String, BigDecimal>> mapCategoriesIncome(Map<String, BigDecimal> totalIncomeForEachCategory){
        return totalIncomeForEachCategory.entrySet().stream().map(entry-> new Tuple<>(entry.getKey(), entry.getValue())).toList();
   }
}
