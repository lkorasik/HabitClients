package com.lkorasik.habitclients;

public enum Priority {
    HIGH("HIGH"),
    MIDDLE("MIDDLE"),
    LOW("LOW");

    private String value;

    Priority(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static Priority fromValue(String value) {
        for (Priority b : Priority.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}


