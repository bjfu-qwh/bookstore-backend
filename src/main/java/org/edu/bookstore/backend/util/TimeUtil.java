package org.edu.bookstore.backend.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String currentTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
