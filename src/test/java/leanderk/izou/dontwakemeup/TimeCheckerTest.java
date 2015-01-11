package leanderk.izou.dontwakemeup;

import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimeCheckerTest {
    public static final String FROM = "from";
    public static final String TO = "to";

    @Test
    public void testMatchesFromAfterCurrentTime() throws Exception {
        String input = createPropertiesValue(FROM, LocalTime.now().plusMinutes(1));
        assertFalse(new TimeChecker(input).matches());
    }

    @Test
    public void testMatchesFromBeforeCurrentTime() throws Exception {
        String input = createPropertiesValue(FROM, LocalTime.now().minusMinutes(1));
        assertTrue(new TimeChecker(input).matches());
    }

    @Test
    public void testMatchesToAfterCurrentTime() throws Exception {
        String input = createPropertiesValue(TO, LocalTime.now().plusMinutes(1));
        assertTrue(new TimeChecker(input).matches());
    }

    @Test
    public void testMatchesMixedAfterCurrentTime() throws Exception {
        String input = createPropertiesValue(FROM, LocalTime.now().minusMinutes(45));
        input = input + " " + createPropertiesValue(TO, LocalTime.now().minusMinutes(20));
        input = input + " " + createPropertiesValue(FROM, LocalTime.now().minusMinutes(10));
        input = input + " " + createPropertiesValue(TO, LocalTime.now().plusMinutes(30));
        assertTrue(new TimeChecker(input).matches());
    }

    public String createPropertiesValue(String id, LocalTime time) {
        return id + " " + time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}