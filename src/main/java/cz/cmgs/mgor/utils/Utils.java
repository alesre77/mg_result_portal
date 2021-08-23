package cz.cmgs.mgor.utils;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Utils {

    public static final String DATE_FORMAT = "dd.MM.YYYY";

    public static String formatDate(Date date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            return dateFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }
}
