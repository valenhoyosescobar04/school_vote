package co.edu.cue.escolarvote.utils;

import co.edu.cue.escolarvote.domain.enums.StudyingDay;

public class Validator {
    public static boolean validateCandidate(String name, String grade, byte[] image, StudyingDay studyingDay) {
        return isNonEmpty(name) && isNonEmpty(grade) && image != null && studyingDay != null;
    }

    private static boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
