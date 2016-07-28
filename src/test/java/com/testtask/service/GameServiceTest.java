package com.testtask.service;

import com.testtask.Connect4gameApplication;
import com.testtask.domain.Game;
import com.testtask.domain.GameRepository;
import com.testtask.domain.MarkType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by etsatsina on 28-Jul-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Connect4gameApplication.class)
public class GameServiceTest {

    public static final String PLAYER = "player1";
    public static final String OPPONENT = "player2";

    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository gameRepository;

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = gameService.createGame(PLAYER, OPPONENT);
    }

    @Test
    public void shouldSaveMove() throws Exception {
        gameService.saveMove(game, PLAYER, 0);

        Game retrievedGame = gameService.retrieveGame(PLAYER , Game.State.CONTINUES);
        int[][] retrievedBoard = retrievedGame.getBoard();

        assertEquals(MarkType.PLAYER, retrievedBoard[0][0]);
    }

    @Test
    public void shouldRetrieveGame() throws Exception {
        Game retrievedGame = gameService.retrieveGame(PLAYER, Game.State.CONTINUES);
        assertEquals(PLAYER, retrievedGame.getPlayerUsername());
    }

    @Test
    public void shouldCreateGame() throws Exception {
        gameRepository.delete(game);
        game = gameService.createGame(PLAYER, OPPONENT);
        assertEquals(PLAYER, game.getPlayerUsername());
    }

    @Test
    public void shouldReturnPlayerAsWinner() throws Exception {
        gameService.saveMove(game, PLAYER, 0);
        gameService.saveMove(game, OPPONENT, 3);
        gameService.saveMove(game, PLAYER, 0);
        gameService.saveMove(game, OPPONENT, 3);
        gameService.saveMove(game, PLAYER, 0);
        gameService.saveMove(game, OPPONENT, 3);
        gameService.saveMove(game, PLAYER, 0);

        assertEquals(Game.State.PLAYER_WINS, game.getState());
    }

    @After
    public void tearDown() throws Exception {
        gameRepository.delete(game);

    }
}