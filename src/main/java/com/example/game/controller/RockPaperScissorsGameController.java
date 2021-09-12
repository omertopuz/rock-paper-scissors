package com.example.game.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.game.exception.AlreadyExistsException;
import com.example.game.exception.EntityNotFoundException;
import com.example.game.model.dto.GameDto;
import com.example.game.model.dto.PlayerDto;
import com.example.game.model.value.EnumMove;
import com.example.game.service.GameService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/players")
public class RockPaperScissorsGameController {

    private final GameService gameService;
    private final ObjectMapper objectMapper;


    public RockPaperScissorsGameController(GameService gameService, ObjectMapper objectMapper) {
        this.gameService = gameService;
        this.objectMapper = objectMapper;
    }

    @ApiOperation(value = "Get All players", produces = "application/json")
    @GetMapping("/")
    public List<PlayerDto> getAllPlayers() throws EntityNotFoundException {
        return objectMapper.convertValue(gameService.getAllPlayers(), new TypeReference<List<PlayerDto>>() {});
    }

    @ApiOperation(value = "Get Player By username, calculates player rank", produces = "application/json")
    @GetMapping("/{username}")
    public PlayerDto getPlayer(@PathVariable String username) throws EntityNotFoundException {
        PlayerDto playerDto = objectMapper.convertValue(gameService.getPlayer(username), PlayerDto.class);
        playerDto.setPlayerRank(gameService.getPlayerRank(playerDto.getId()));

        return playerDto;
    }

    @ApiOperation(value = "Play Rock, Paper, Scissors game, Player Moves should be any ofROCK,PAPER,SCISSORS. If player does not exist with given username, it is created auto and plays the game.", produces = "application/json")
    @PostMapping("/{username}/games/{playerMove}")
    public GameDto playGame(@PathVariable String username,@RequestBody String playerMove) throws AlreadyExistsException, EntityNotFoundException {
        return objectMapper.convertValue(gameService.playGame(username, EnumMove.valueOf(playerMove)), GameDto.class);
    }
}
