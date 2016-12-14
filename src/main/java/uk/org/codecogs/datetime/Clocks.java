package uk.org.codecogs.datetime;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Useful in unit tests for setting the clock to a deterministic value.
 */
public final class Clocks {
    private static Clock clock = Clock.systemDefaultZone();
    private static final ZoneId defaultZoneId = clock.getZone();

    public static Clock getClock() {
        return clock;
    }

    public static void useSystemDefaultClock() {
        clock = Clock.systemDefaultZone();
    }

    public static void useFixedClockAt(LocalDate date) {
        useFixedClockAt(date.atStartOfDay());
    }

    public static void useFixedClockAt(LocalDateTime dateTime) {
        useFixedClockAt(dateTime, defaultZoneId);
    }


    public static void useFixedClockAt(LocalDateTime dateTime, ZoneId zoneId) {
       useFixedClockAt(dateTime.atZone(zoneId).toInstant(), zoneId);
    }

    private static void useFixedClockAt(Instant instant, ZoneId zoneId) {
        clock = Clock.fixed(instant, zoneId);
    }
}
