package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    public static String getFollowingDay(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalDate nextDate = localDate.plusDays(1);
        return nextDate.format(formatter);
    }

    public static String getToday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.now().format(formatter);
    }
}
