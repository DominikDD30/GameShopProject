package com.project.gameHubBackend.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface OffsetDateTimeMapper {

    DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter DATE_FORMAT_SHORT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Named("mapOffsetDateTimeToString")
    default String mapOffsetDateTimeToString(OffsetDateTime offsetDateTime) {
        return Optional.ofNullable(offsetDateTime)
            .map(odt -> offsetDateTime.atZoneSameInstant(ZoneOffset.UTC))
            .map(odt -> odt.format(DATE_FORMAT))
            .orElse(null);
    }

    @Named("mapOffsetDateTimeToShortDateString")
    default String mapOffsetDateTimeToShortDateString(OffsetDateTime offsetDateTime) {
        return Optional.ofNullable(offsetDateTime)
                .map(odt -> offsetDateTime.atZoneSameInstant(ZoneOffset.UTC))
                .map(odt -> odt.format(DATE_FORMAT_SHORT))
                .orElse(null);
    }
}
