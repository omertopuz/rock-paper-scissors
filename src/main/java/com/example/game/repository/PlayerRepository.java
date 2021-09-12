package com.example.game.repository;

import com.example.game.model.entity.Game;
import com.example.game.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    Optional<Player> findByUsername(String username);

    List<Player> findAllByOrderByTotalScoreDesc();

//    @Query("from Player p where p.totalScore >= (select p.totalScore from Player p where p.id = :playerId)")
//    List<Player> getPlayerRank(@Param("playerId") Long playerId);

    @Query("from Game g inner join fetch g.player where g.player.id = :playerId")
    List<Game> getPlayerGames(@Param("playerId") Long playerId);

    @Query("select p.totalScore from Player p where p.totalScore >= (select p.totalScore from Player p where p.id = :playerId) group by p.totalScore order by p.totalScore desc")
    List<Long> getPlayerRanksGreaterThanGivenPlayer(@Param("playerId") Long playerId);
}
