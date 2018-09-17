package com.reporting.iris.irissearch.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class DateUtilImpl implements DateUtil {

    DateTimeFormatter fullFormatterFromStringPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    //        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Override
    public String localDateTimeToString(final LocalDateTime localDateTime) {
        return localDateTime.format(dateOnlyFormatter);
    }

    @Override
    public LocalDateTime StringToLocalDateTime(final String stringDate) {
        return LocalDateTime.parse(stringDate, fullFormatterFromStringPattern);
    }

    /**
     * Converting java.util.Date to java.time.LocalDate
     */
    @Override
    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }

    /**
     * Converting java.util.Date to java.time.LocalDateTime
     */
    @Override
    public LocalDateTime convertToLocalDateTo(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }


    /**
     * Convert java.time.LocalDate to java.util.Date
     */
    @Override
    public Date convertLocalDateToDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant());
    }

    /**
     * Convert java.time.LocalDateTime to java.util.Date
     */
    @Override
    public Date convertLocalDateToDate(LocalDateTime dateToConvert) {
        return java.util.Date
            .from(dateToConvert.atZone(ZoneId.systemDefault())
                .toInstant());
    }


}
