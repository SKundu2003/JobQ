package com.jobq.Job_server.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private static final SimpleDateFormat IST_FORMAT;

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    static {
        IST_FORMAT = new SimpleDateFormat(DATE_FORMAT);
        IST_FORMAT.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
    }


    public static String getItcDate() {
        Date date = new Date();
        return IST_FORMAT.format(date);
    }
}
