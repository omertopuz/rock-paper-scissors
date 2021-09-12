package com.example.game.model.dto;

import com.example.game.model.value.EnumMove;

public class GameDto {

    private EnumMove playerMove;

    private EnumMove computerMove;

    private int score;

    public EnumMove getPlayerMove() {
        return playerMove;
    }

    public void setPlayerMove(EnumMove playerMove) {
        this.playerMove = playerMove;
    }

    public EnumMove getComputerMove() {
        return computerMove;
    }

    public void setComputerMove(EnumMove computerMove) {
        this.computerMove = computerMove;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
