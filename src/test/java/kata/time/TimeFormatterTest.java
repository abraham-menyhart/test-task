package kata.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TimeFormatterTest {

    @Nested
    @DisplayName("Kata sample cases")
    class SampleCases {

        @ParameterizedTest(name = "{0} s → \"{1}\"")
        @CsvSource({
                "62, '1 minute and 2 seconds'",
                "3662, '1 hour, 1 minute and 2 seconds'"
        })
        void kataSamples(int seconds, String expected) {
            assertEquals(expected, new TimeFormatter().formatDuration(seconds));
        }
    }

    @Nested
    @DisplayName("Edge cases")
    class EdgeCases {

        @ParameterizedTest(name = "{0} s → \"{1}\"")
        @CsvSource({
                "0, 'now'",
                "1, '1 second'",
                "59, '59 seconds'",
                "60, '1 minute'",
                "61, '1 minute and 1 second'",
                "3600, '1 hour'",
                "3601, '1 hour and 1 second'",
                "3661, '1 hour, 1 minute and 1 second'",
                "86400, '1 day'",
                "31536000, '1 year'"
        })
        void simpleBoundaries(int seconds, String expected) {
            assertEquals(expected, new TimeFormatter().formatDuration(seconds));
        }
    }

    @Nested
    @DisplayName("Comprehensive mixed values")
    class MixedValues {

        @ParameterizedTest(name = "{0} s → \"{1}\"")
        @CsvSource({
                "15731080, '182 days, 1 hour, 44 minutes and 40 seconds'",
                "132030240, '4 years, 68 days, 3 hours and 4 minutes'",
                "205851834, '6 years, 192 days, 13 hours, 3 minutes and 54 seconds'",
                "253374061, '8 years, 12 days, 13 hours, 41 minutes and 1 second'",
                "242062374, '7 years, 246 days, 15 hours, 32 minutes and 54 seconds'",
                "101956166, '3 years, 85 days, 1 hour, 9 minutes and 26 seconds'",
                "33243586, '1 year, 19 days, 18 hours, 19 minutes and 46 seconds'"
        })
        void mixedBigger(int seconds, String expected) {
            assertEquals(expected, new TimeFormatter().formatDuration(seconds));
        }
    }

    @Test
    @DisplayName("Pluralisation sanity check")
    void pluralisation() {
        assertEquals("2 minutes and 1 second", new TimeFormatter().formatDuration(121));
        assertEquals("1 year and 1 second", new TimeFormatter().formatDuration(31536001));
    }
}
