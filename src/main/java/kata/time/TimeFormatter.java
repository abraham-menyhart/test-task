package kata.time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TimeFormatter {

    public String formatDuration(long totalSeconds) {
        if (totalSeconds < 0) {
            throw new IllegalArgumentException("Duration must be non-negative");
        }
        if (totalSeconds == 0) {
            return "now";
        }

        List<Component> components = buildComponentList(totalSeconds);
        return join(components);
    }

    private List<Component> buildComponentList(long secondsLeft) {
        if (secondsLeft == 0) return Collections.emptyList();

        List<Component> result = new ArrayList<>();

        for (Unit unit : Unit.values()) {
            long qty = secondsLeft / unit.seconds;
            if (qty > 0) {
                result.add(new Component(qty, unit.label(qty)));
                secondsLeft %= unit.seconds;
            }
        }
        return result;
    }

    private String join(List<Component> parts) {
        Objects.requireNonNull(parts);
        int size = parts.size();

        switch (size) {
            case 0 -> {
                return "";
            }
            case 1 -> {
                return parts.getFirst().toString();
            }
            case 2 -> {
                return parts.get(0) + " and " + parts.get(1);
            }
            default -> {
                String head = parts.subList(0, size - 1)
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));
                return head + " and " + parts.get(size - 1);
            }
        }
    }
}
