package com.fm.mcs.domain.enumeration;

/**
 * The NoteEncadrantReferent enumeration.
 */
public enum NoteEncadrantReferent {
    NonAcquis("Non acquis"),
    EnCoursAquisition("En cours d’acquisition"),
    Acquis("Acquis");

    private final String value;

    NoteEncadrantReferent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
