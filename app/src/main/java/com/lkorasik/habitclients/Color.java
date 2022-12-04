package com.lkorasik.habitclients;

public enum Color {
    RED("RED"),
    ORANGE("ORANGE"),
    YELLOW("YELLOW"),
    LIGHT_GREEN("LIGHT_GREEN"),
    GREEN("GREEN");

    private String value;

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static Color fromValue(String value) {
        for (Color b : Color.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
