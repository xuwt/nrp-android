package com.nrp.android.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());

    public static String getTime(long time) {
        Date date = new Date();
        date.setTime(time);
        return format.format(date);
    }


    public static String parseTimeFormat(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date date = new Date(time);
        return formatter.format(date);
    }
}
