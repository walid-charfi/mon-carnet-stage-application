package com.fm.mcs.domain.enumeration;

/**
 * The Hopital enumeration.
 */
public enum Hopital {
    CharlesNicolle("Charles Nicolle"),
    MongiSlim("Mongi Slim"),
    HabibThameur("Habib Thameur"),
    AzizaOthmana("Aziza Othmana"),
    LaRabta("La Rabta"),
    SalahAzaiez("Salah Azaiez"),
    HopitalEnfants("Hôpital d&#39;Enfants"),
    Razi("Razi");

    private final String value;

    Hopital(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
