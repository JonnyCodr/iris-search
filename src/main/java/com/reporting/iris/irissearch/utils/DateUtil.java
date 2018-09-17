package com.reporting.iris.irissearch.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface DateUtil {

    String localDateTimeToString(LocalDateTime localDateTime);
    LocalDateTime StringToLocalDateTime(final String stringDate);
    LocalDate convertToLocalDateViaInstant(Date dateToConvert);
    LocalDateTime convertToLocalDateTo(Date dateToConvert);
    Date convertLocalDateToDate(LocalDate dateToConvert);
    Date convertLocalDateToDate(LocalDateTime dateToConvert);

}
