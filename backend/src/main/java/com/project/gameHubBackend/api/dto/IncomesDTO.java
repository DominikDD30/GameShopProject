package com.project.gameHubBackend.api.dto;

import com.project.gameHubBackend.util.DatePair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomesDTO {
    private List<IncomeDTO> incomes;
    private BigDecimal totalIncome;
    private DatePair bestDate;
    private BigDecimal bestDateIncomeAmount;
}


