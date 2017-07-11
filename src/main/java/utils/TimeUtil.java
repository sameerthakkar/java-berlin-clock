package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static utils.Constants.TIME24HOURS_PATTERN;

public class TimeUtil {

    /**
     * Validate time in 24 hours format with regular expression
     * @param time time address for validation
     * @return true valid time fromat, false invalid time format
     */

    public static boolean validateTime(String time){
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(TIME24HOURS_PATTERN);
        matcher = pattern.matcher(time);
        return matcher.matches();
    }

    public static int getSeconds(String time){
        return Integer.parseInt(time.substring(6));
    }

    public static int getMinutes(String time){
        return Integer.parseInt(time.substring(3,5));
    }

    public static int getHours(String time){
        return Integer.parseInt(time.substring(0,2));
    }

}
