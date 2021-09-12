package com.example.game.model.dto;

import java.util.List;

public class PlayerDto {

    private Long id;

    private String username;

    private long totalScore;

    private List<GameDto> games;

    private long playerRank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(long totalScore) {
        this.totalScore = totalScore;
    }


    public List<GameDto> getGames() {
        return games;
    }

    public void setGames(List<GameDto> games) {
        this.games = games;
    }

    public long getPlayerRank() {
        return playerRank;
    }

    public void setPlayerRank(long playerRank) {
        this.playerRank = playerRank;
    }
}
