package com.testtask.service;

import com.testtask.domain.Game;
import com.testtask.domain.GameRepository;
import com.testtask.domain.MarkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Liza on 27-Jul-16.
 */
@Service
public class GameService {

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerService playerService;

    @Autowired
    WinnerCheckService winnerCheckService;

    public Game retrieveGame(String username, Game.State state) {
        Game retrievedGameForPlayer = gameRepository.findByPlayerUsernameAndState(username, state);

        if (retrievedGameForPlayer == null) {
            return gameRepository.findByOpponentUsernameAndState(username, state);
        }

        return retrievedGameForPlayer;
    }

    public Game createGame(String playerUsername, String opponentUsername) {
        Game game = new Game();
        resetGame(game, playerUsername, opponentUsername);
        gameRepository.save(game);

        return game;
    }

    private void resetGame(Game game, String playerUsername, String opponentUsername) {
        int columnsNumber = configurationService.getColumnsNumber();
        int rowsNumber = configurationService.getRowsNumber();
        int [][] board = new int[columnsNumber][rowsNumber];

        game.setId(generateId());
        game.setBoard(board);
        game.setPlayerUsername(playerUsername);
        game.setOpponentUsername(opponentUsername);
        game.setState(Game.State.CONTINUES);
        game.setUsernameMarkMap(getUsernameMarkMap(playerUsername, opponentUsername));
    }

    public Game saveMove(Game game, String username, int column) {
        boolean successfulMove = playerService.makeMove(game, username, column);

        if (successfulMove) {
            winnerCheckService.checkAndUpdateWinner(game, column);
            gameRepository.save(game);
        }

        return game;

    }

    private HashMap<String, Integer> getUsernameMarkMap (String playerUsername, String opponentUsername) {
        HashMap<String, Integer> usernameMarkMap = new HashMap<>(2);

        usernameMarkMap.put(playerUsername, MarkType.PLAYER);
        usernameMarkMap.put(opponentUsername, MarkType.OPPONENT);

        return usernameMarkMap;
    }


    private String generateId() {
        return UUID.randomUUID().toString();
    }

}
