package com.example.game.service;

import com.example.game.exception.AlreadyExistsException;
import com.example.game.exception.EntityNotFoundException;
import com.example.game.model.entity.Player;
import com.example.game.repository.PlayerRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Selects a player by id.
     *
     * @param playerId
     * @return found player
     * @throws EntityNotFoundException if no player with the given id was found.
     */
    public Player find(Long playerId) throws EntityNotFoundException{
        return findPlayerChecked(playerId);
    }


    /**
     * Creates a new player.
     *
     * @param player
     * @return
     * @throws AlreadyExistsException if a player already exists with the given username, ... .
     */
    public Player create(Player player) throws AlreadyExistsException {
        Player newPlayer;
        try
        {
            newPlayer = playerRepository.save(player);
        }
        catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException(e.getMessage());
        }
        return newPlayer;
    }

    private Player findPlayerChecked(Long playerId) throws EntityNotFoundException {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + playerId));
    }
}
