package com.example.game.service;

import com.example.game.exception.EntityNotFoundException;
import com.example.game.model.value.EnumMove;
import com.example.game.model.entity.Game;
import com.example.game.model.entity.Player;
import com.example.game.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void test_GetPlayerGames() {
        long playerId = 3l;
        List<Game> playerGames = playerRepository.getPlayerGames(playerId);
        assertTrue(playerGames.size()>0);
    }

    @Test
    void test_RankPlayers() {
        List<Player> playersOrdered = playerRepository.findAllByOrderByTotalScoreDesc();
        List<Player> allPlayers = playerRepository.findAll();
        Player firstPlayer = allPlayers.stream().sorted((o1, o2) -> -1 * Long.compare(o1.getTotalScore(),o2.getTotalScore()))
                .findFirst().get();
        assertTrue(playersOrdered.get(0).getId() == firstPlayer.getId());
    }

    @Test
    void test_PlayGame_CheckGameSaved_PlayerTotalScoreUpdated() throws EntityNotFoundException {
        long playerId = 3l;
        EnumMove playerMove = EnumMove.PAPER;
        Player player = playerRepository.findById(playerId).get();
        Game game = gameService.playGame(playerId,playerMove);
        assertNotNull(game);
        List<Game> playerGames = playerRepository.getPlayerGames(playerId);

        int totalScore = playerGames.stream().mapToInt(m->m.getScore())
                .reduce(0,(a,b)->a+b);
        assertTrue(player.getTotalScore() + game.getScore() == totalScore);
        assertTrue(playerGames.stream().filter(f->f.getId() == game.getId()).findFirst().isPresent());
    }
}