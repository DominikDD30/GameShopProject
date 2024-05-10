package com.project.gameHubBackend.api.dto;

import com.project.gameHubBackend.util.DatePair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDTO{
    private DatePair datePair;
    private BigDecimal amount;
}

