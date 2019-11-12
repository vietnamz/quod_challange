package timeutil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

/**
 * The type Time util.
 * This class is used to convert the date time string to LocalDateTime
 */
public class TimeUtil {

    /**
     * Convert string to date time optional.
     *
     * @param dateTime the date time
     * @return the optional
     */
    public static Optional<LocalDateTime> convertStringToDateTime(String dateTime) {
        try {
            return Optional.of(LocalDateTime.ofInstant(Instant.parse(dateTime), ZoneId.of("Zulu")));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
