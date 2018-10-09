package org.foresee.binhu.share;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String join(String delimiter, String... strings){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            builder.append(strings[i]);
            if(i!=strings.length-1){
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }
    public static String join(String delimiter, Iterable<? extends String> strings) {
        StringBuilder builder = new StringBuilder();
        for (String str : strings) {
            builder.append(str);
            if (strings.iterator().hasNext()) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }
    private static final String DATE_TIME_PATTERN="yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat DATE_FORMATER=new SimpleDateFormat(DATE_TIME_PATTERN);
    public static final Date DEFAULT_DATE=new Date(1994,7,21,13,05,00);

    public static String formatDate(Date date){
        return DateFormat.format(DATE_TIME_PATTERN, date).toString();
    }
    public static Date parseDate(String dateStr) throws ParseException {
        return DATE_FORMATER.parse(dateStr);
    }
    public static boolean isNetworkAvailableAndConnected(Activity activity) {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable &&
                cm.getActiveNetworkInfo().isConnected();
        return isNetworkConnected;
    }
}
