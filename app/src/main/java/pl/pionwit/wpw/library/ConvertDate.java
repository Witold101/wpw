package pl.pionwit.wpw.library;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Witold on 04.01.2016.
 */
public class ConvertDate {

    public static String dateToString(Date date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss", Locale.ENGLISH);
         return format.format(date);
    }

    public static Date stringToDate(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss", Locale.ENGLISH);
        return format.parse(date);
    }

}
