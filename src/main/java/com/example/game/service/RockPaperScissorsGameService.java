package com.example.game.service;

import com.example.game.model.entity.Game;
import com.example.game.model.value.EnumMove;
import com.example.game.model.value.EnumWinner;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RockPaperScissorsGameService {
    /**
     * plays Rock, Paper, Scissors against a human
     *
     * @param playerMove
     * @return Game object which stores moves
     */
    public Game playGame(EnumMove playerMove){
        Game game = new Game();
        game.setComputerMove(getComputerMove());
        game.setPlayerMove(playerMove);
        EnumWinner winner = findWinner(game.getPlayerMove(),game.getComputerMove());
        game.setScore(winner.getPoint());
        return game;
    }

    /**
     * finds the winner of the game by performing the rules below
     * - Rock beats Scissors but loses to Paper.
     * - Paper beats Rock but loses to Scissors.
     * - Scissors beats Paper but loses to Rock.
     *
     * @param playerMove
     * @param computerMove
     * @return winner of the game
     */
    public EnumWinner findWinner(EnumMove playerMove, EnumMove computerMove){
        //EnumMove computerMove = getComputerMove();

        if (playerMove == computerMove) {
            return EnumWinner.TIE;
        }
        // playerMove: ROCK
        else if (playerMove == EnumMove.ROCK)
            return computerMove == EnumMove.PAPER ?
                    EnumWinner.COMPUTER
                    :EnumWinner.PLAYER;
        // playerMove: PAPER
        else if (playerMove == EnumMove.PAPER)
            return computerMove == EnumMove.SCISSORS ?
                    EnumWinner.COMPUTER
                    :EnumWinner.PLAYER;

        // playerMove: SCISSORS
        else
            return computerMove == EnumMove.ROCK ?
                    EnumWinner.COMPUTER
                    :EnumWinner.PLAYER;
    }

    public EnumMove getComputerMove() {
        Random random = new Random();
        return EnumMove.intToEnum(random.nextInt(3)+1);
    }
}
