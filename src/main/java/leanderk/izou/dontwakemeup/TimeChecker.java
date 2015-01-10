package leanderk.izou.dontwakemeup;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
        LocalTime currentTime = LocalTime.now();
        if (input.matches("((from|to)\\s\\d\\d:\\d\\d\\s?)+")) {
            Matcher matcher = Pattern.compile("(from\\s\\d\\d:\\d\\d?)*").matcher(input);
            List<String> groups = new ArrayList<>();
            while (matcher.find()) {
                groups.add(matcher.group());
            }
            LocalTime closestFrom = getClosestTime(currentTime, groups);
            matcher = Pattern.compile("(to\\s\\d\\d:\\d\\d?)*").matcher(input);
            groups.clear();
            while (matcher.find()) {
                groups.add(matcher.group());
            }
            LocalTime closestTo = getClosestTime(currentTime, groups);
            if (closestFrom == null && closestTo != null) {
                return true;
            } else if (closestFrom != null && closestTo == null) {
                return false;
            } else {
                //noinspection ConstantConditions
                return closestTo.isBefore(closestFrom);
            }
        }
        return false;
    }

    private LocalTime getClosestTime(LocalTime currentTime, List<String> groups) {
        LocalTime closest = null;
        for(String group : groups) {
            LocalTime time = getTimeFromString(group);
            if (closest == null || (!time.isBefore(currentTime) && time.isBefore(closest))) {
                closest = time;
            }
        }
        return closest;
    }

    private LocalTime getTimeFromString(String input) {
        Matcher matcher = Pattern.compile("(\\d\\d:\\d\\d?)*").matcher(input);
        if (matcher.find()) {
            String group = matcher.group(1);
            String[] parts = group.split(":");
            return LocalTime.of(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
        }
        return null;
    }
}
