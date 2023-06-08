package java8.functional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MastersExercise4 {

    /**
     * Display xmas day - ph
     * Equivalent day to - us
     * Remaining months and days before xmas ph
     * days before xmas
     *
     * @param args
     */

    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd, YYYY hh:mm a");

    public static void main(String[] args) {

        final LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        final LocalDateTime xmasDay = LocalDateTime.of(2023, 12, 25, 0, 0, 0);

        final long monthsBeforeXmas = now.until(xmasDay, ChronoUnit.MONTHS);
        final long decemberDaysBeforeXmas = now.plusMonths(monthsBeforeXmas)
                .until(xmasDay, ChronoUnit.DAYS);

        final ZonedDateTime asiaXmas = xmasDay.atZone(ZoneId.of("Asia/Shanghai"));
        final ZonedDateTime usXmas = asiaXmas.withZoneSameInstant(ZoneId.of("America/Chicago"));

        System.err.println(String.format("PH: %s (Asia/Shanghai)", FORMATTER.format(asiaXmas)));
        System.err.println(String.format("US: %s (America/Chicago)", FORMATTER.format(usXmas)));
        System.err.println(String.format("Remaining %d months(s) and %d day(s) before XMAS", monthsBeforeXmas, decemberDaysBeforeXmas));

        System.err.println(String.format("Days before XMAS %d", now.until(xmasDay, ChronoUnit.DAYS)));
    }

}
