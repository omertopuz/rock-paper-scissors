package com.example.game.model.entity;

import com.example.game.model.value.EnumMove;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumMove playerMove;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumMove computerMove;

    @Column(nullable = false)
    private int score;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
