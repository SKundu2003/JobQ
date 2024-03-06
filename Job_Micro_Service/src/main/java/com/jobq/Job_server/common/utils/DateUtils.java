package com.jobq.Job_server.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private static final SimpleDateFormat UTC_FORMAT;

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    static {
        UTC_FORMAT = new SimpleDateFormat(DATE_FORMAT);
        UTC_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }


    public static String getUtcDate() {
        Date date = new Date();
        return UTC_FORMAT.format(date);
    }
}
