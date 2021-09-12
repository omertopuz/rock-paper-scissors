package com.example.game.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "player",
        uniqueConstraints = @UniqueConstraint(name = "p_username", columnNames = {"username"}))
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private long totalScore;

    @OneToMany(mappedBy = "player",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Game> games;

    public void addGame(Game game){
        if(games == null)
            games = new ArrayList<>();
        games.add(game);
        game.setPlayer(this);
    }



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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
