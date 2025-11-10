package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static LocalDate getFollowingDay(LocalDate date) {
        return date.plusDays(1);
    }

    // Return today's date as LocalDate
    public static LocalDate getToday() {
        return LocalDate.now();
    }
}
