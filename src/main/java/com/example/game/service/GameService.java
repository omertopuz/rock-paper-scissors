package com.example.game.service;

import com.example.game.exception.AlreadyExistsException;
import com.example.game.exception.EntityNotFoundException;
import com.example.game.model.value.EnumMove;
import com.example.game.model.entity.Game;
import com.example.game.model.entity.Player;
import com.example.game.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final PlayerRepository playerRepository;

    private final PlayerService playerService;
    private final RockPaperScissorsGameService rockPaperScissorsGameService;

    public GameService(PlayerRepository playerRepository, PlayerService playerService, RockPaperScissorsGameService rockPaperScissorsGameService) {
        this.playerRepository = playerRepository;
        this.playerService = playerService;
        this.rockPaperScissorsGameService = rockPaperScissorsGameService;
    }

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public Player getPlayer(String username) throws EntityNotFoundException {
        return playerRepository.findByUsername(username)
                .orElseThrow(()->new EntityNotFoundException("Player not found: " + username));
    }

    @Transactional( propagation = Propagation.REQUIRED)
    public Game playGame(String username, EnumMove playerMove) throws EntityNotFoundException, AlreadyExistsException {
        Optional<Player> playerTemp = playerRepository.findByUsername(username);
        if (playerTemp.isPresent()){
            return playGame(playerTemp.get().getId(),playerMove);
        }else {
            Player player = new Player();
            player.setUsername(username);
            player = playerService.create(player);
            return playGame(player.getId(),playerMove);
        }

    }

    @Transactional( propagation = Propagation.REQUIRED)
    public Game playGame(long playerId, EnumMove playerMove) throws EntityNotFoundException {
        Player player = playerService.find(playerId);
        Game game = rockPaperScissorsGameService.playGame(playerMove);
        if (game.getScore() != 0)
            player.setTotalScore(player.getTotalScore() + game.getScore());
        player.addGame(game);

        playerRepository.save(player);
        return player.getGames().get(player.getGames().size()-1);
    }

    public List<Player> getAllPlayerRanks(){
        return playerRepository.findAllByOrderByTotalScoreDesc();
    }

    public long getPlayerRank(long playerId) throws EntityNotFoundException {
        List<Long> allRanksGreaterThan = playerRepository.getPlayerRanksGreaterThanGivenPlayer(playerId);
        Player player = playerService.find(playerId);
        long rank = 0;
        for (Long l:allRanksGreaterThan) {
            rank++;
            if (player.getTotalScore() == l)
                break;
        }
        return rank;
    }
}
