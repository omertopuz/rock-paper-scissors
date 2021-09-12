package com.example.game.service;

import com.example.game.model.entity.Game;
import com.example.game.model.value.EnumMove;
import com.example.game.model.value.EnumWinner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsGameServiceTest {

    private RockPaperScissorsGameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new RockPaperScissorsGameService();
    }

    @Test
    void test_BothPlayerAndComputerChooseSameOption_ExpectTie(){
        EnumWinner winner = gameService.findWinner(EnumMove.ROCK,EnumMove.ROCK);
        assertEquals(winner,EnumWinner.TIE);
    }

    @Test
    void test_PlayerChooseROCK_ComputerChooseSCISSORS_ExpectPlayer(){
        EnumWinner winner = gameService.findWinner(EnumMove.ROCK,EnumMove.SCISSORS);
        assertEquals(winner,EnumWinner.PLAYER);
    }

    @Test
    void test_PlayerChooseROCK_ComputerChoosePAPER_ExpectComputer(){
        EnumWinner winner = gameService.findWinner(EnumMove.ROCK,EnumMove.PAPER);
        assertEquals(winner,EnumWinner.COMPUTER);
    }

    @Test
    void test_PlayerChoosePAPER_ComputerChooseROCK_ExpectPlayer(){
        EnumWinner winner = gameService.findWinner(EnumMove.PAPER,EnumMove.ROCK);
        assertEquals(winner,EnumWinner.PLAYER);
    }

    @Test
    void test_PlayerChoosePAPER_ComputerChooseSCISSORS_ExpectComputer(){
        EnumWinner winner = gameService.findWinner(EnumMove.PAPER,EnumMove.SCISSORS);
        assertEquals(winner,EnumWinner.COMPUTER);
    }

    @Test
    void test_PlayerChooseSCISSORS_ComputerChoosePAPER_ExpectPlayer(){
        EnumWinner winner = gameService.findWinner(EnumMove.SCISSORS,EnumMove.PAPER);
        assertEquals(winner,EnumWinner.PLAYER);
    }

    @Test
    void test_PlayerChooseSCISSORS_ComputerChooseROCK_ExpectComputer(){
        EnumWinner winner = gameService.findWinner(EnumMove.SCISSORS,EnumMove.ROCK);
        assertEquals(winner,EnumWinner.COMPUTER);
    }

    @Test
    void test_RandomMove(){
        Set<EnumMove> moves = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            moves.add(gameService.getComputerMove());
        }
        assertTrue(moves.size()>=3);
    }

    @Test
    void test_PlayGameAgainstHuman(){
        EnumMove playerMove = EnumMove.ROCK;
        Game game = gameService.playGame(playerMove);
        EnumWinner winner = gameService.findWinner(playerMove,game.getComputerMove());
        assertEquals(winner,EnumWinner.intToEnum(game.getScore()));
    }
}