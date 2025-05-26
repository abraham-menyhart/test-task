package kata.time;

enum Unit {
    YEAR  (31_536_000, "year"),
    DAY   (86_400,     "day"),
    HOUR  (3_600,      "hour"),
    MINUTE(60,         "minute"),
    SECOND(1,          "second");

    final long seconds;
    final String name;

    Unit(long seconds, String name) {
        this.seconds = seconds;
        this.name    = name;
    }

    /** English name with correct pluralisation for {@code qty}. */
    String label(long qty) {
        return qty == 1 ? name : name + "s";
    }
}
