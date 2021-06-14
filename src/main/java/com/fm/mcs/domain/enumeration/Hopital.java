package com.fm.mcs.domain.enumeration;

/**
 * The Hopital enumeration.
 */
public enum Hopital {
    CharlesNicoles("Charles Nicole");

    private final String value;

    Hopital(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
