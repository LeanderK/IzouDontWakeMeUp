package leanderk.izou.dontwakemeup;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LeanderK
 * @version 1.0
 */
public class TimeChecker {
    private String input;

    /**
     * input time-span specified in defaultProperties.txt
     * @param input the input
     */
    public TimeChecker(String input) {
        this.input = input;
    }

    /**
     * if the current-time is in the time-span, return true
     * @return true if the current time is in the time span
     */
    public boolean matches() {
        input = input.trim();
        if (!input.matches("((from|to)\\s\\d\\d:\\d\\d\\s?)+"))
            return false;
        LocalTime currentTime = LocalTime.now();
        Matcher matcher = Pattern.compile("((from|to)\\s\\d\\d:\\d\\d)").matcher(input);
        LocalTime tempTime = null;
        while (matcher.find()) {
            String part = matcher.group(1);
            boolean isFrom = part.contains("from");
            LocalTime time = LocalTime.parse(part.replaceAll("(from|to)\\s", ""), DateTimeFormatter.ofPattern("HH:mm"));
            if (isFrom) {
                if (tempTime == null || (tempTime.isAfter(time))) {
                    tempTime = time;
                }
            } else {
                if (tempTime == null) {
                    if (currentTime.isBefore(time))
                        return true;
                } else {
                    if (currentTime.isAfter(tempTime) && currentTime.isBefore(time)) {
                        return true;
                    //for example over night (18:00 to 08:00)
                    } else if (time.isBefore(tempTime)) {
                        if (!(currentTime.isBefore(tempTime) && currentTime.isAfter(time)))
                            return true;
                    }
                    tempTime = null;
                }
            }
        }
        return tempTime != null && tempTime.isBefore(currentTime);
    }
}
