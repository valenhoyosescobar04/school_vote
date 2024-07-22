package co.edu.cue.escolarvote.domain.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum StudyingDay{
    MORNING("Mañana"),
    AFTERNOON("Tarde");

    private final String label;

    StudyingDay(String label) {
        this.label = label;
    }

    public static List<String> studyingDayList() {
        return Arrays.stream(StudyingDay.values())
                .map(StudyingDay::getLabel)
                .collect(Collectors.toList());
    }

    public String getLabel() {
        return label;
    }

    public static StudyingDay fromLabel(String label) {
        for (StudyingDay day : StudyingDay.values()) {
            if (day.getLabel().equalsIgnoreCase(label)) {
                return day;
            }
        }
        throw new IllegalArgumentException("No enum constant for label: " + label);
    }
}
