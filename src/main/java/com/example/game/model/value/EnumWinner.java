package com.example.game.model.value;

public enum EnumWinner {
    PLAYER(1), COMPUTER(-1),TIE(0);

    private int point;

    private EnumWinner(int point) {
        this.point = point;
    }
    public int getPoint() {
        return this.point;
    }
    public static EnumWinner intToEnum(int point) {
        for (EnumWinner type : values()) {
            if (type.point == point) {
                return type;
            }
        }
        return null;
    }
}
