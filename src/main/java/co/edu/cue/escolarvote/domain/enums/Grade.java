package co.edu.cue.escolarvote.domain.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Grade {
    PRIMER_GRADO("Primer grado"),
    SEGUNDO_GRADO("Segundo grado"),
    TERCER_GRADO("Tercer grado"),
    CUARTO_GRADO("Cuarto grado"),
    QUINTO_GRADO("Quinto grado"),
    SEXTO_GRADO("Sexto grado"),
    SÉPTIMO_GRADO("Séptimo grado"),
    OCTAVO_GRADO("Octavo grado"),
    NOVENO_GRADO("Noveno grado"),
    DÉCIMO_GRADO("Décimo grado"),
    UNDÉCIMO_GRADO("Undécimo grado");

    private final String label;

    Grade(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static List<String> gradesList() {
        return Arrays.stream(Grade.values())
                .map(Grade::getLabel)
                .collect(Collectors.toList());
    }

}