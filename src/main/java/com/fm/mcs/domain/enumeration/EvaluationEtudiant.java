package com.fm.mcs.domain.enumeration;

/**
 * The EvaluationEtudiant enumeration.
 */
public enum EvaluationEtudiant {
    NoDemo("Pas de démonstration"),
    Simule("Simulé"),
    Realise("Réalisé");

    private final String value;

    EvaluationEtudiant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
