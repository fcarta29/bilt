package com.byteknowledge.bilt.controller;

import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZonedDateTime;


public abstract class AbstractController {

    protected static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    protected long getStartOfMonthInEpochMilli(final ZonedDateTime zonedDateTime) {
        return zonedDateTime.withDayOfMonth(1).with(LocalTime.MIN).toInstant().toEpochMilli();
    }

    protected long getEndOfMonthInEpochMilli(final ZonedDateTime zonedDateTime) {
        return zonedDateTime.withDayOfMonth(YearMonth.now().lengthOfMonth()).with(LocalTime.MAX).toInstant()
                .toEpochMilli();
    }

}
