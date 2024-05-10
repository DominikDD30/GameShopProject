package com.project.gameHubBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TimePeriod {
    YEARS ("YEAR"),
    MONTHS("MONTH_OF_YEAR"),
    DAYS("DAY_OF_MONTH");

    private final String chronoFieldName;
}
