package com.example.game.model.value;

public enum EnumMove {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private int id;

    private EnumMove(int id) {
        this.id = id;
    }

    public static EnumMove intToEnum(int id) {
        for (EnumMove type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}
