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
public class PlayerServiceTest {

    public static final int FIRST_COlUMN_NUMBER = 0;
    public static final int INVALID_COLUMN_NUMBER = 10;
    public static final String PLAYER = "player1";
    public static final String OPPONENT = "player2";

    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;

    @Autowired
    GameRepository gameRepository;

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = gameService.createGame(PLAYER, OPPONENT);
    }

    @Test
    public void shouldMakeMove() throws Exception {
        boolean result = playerService.makeMove(game, PLAYER, FIRST_COlUMN_NUMBER);
        assertTrue(result);
    }

    @Test
    public void shouldNotMakeMoveOutOfBoardRange() throws Exception {
        boolean result = playerService.makeMove(game, PLAYER, INVALID_COLUMN_NUMBER);
        assertFalse(result);
    }

    @Test
    public void shouldNotMakeMoveWithWinnerState() throws Exception {
        game.setState(Game.State.PLAYER_WINS);
        boolean result = playerService.makeMove(game, PLAYER, FIRST_COlUMN_NUMBER);
        assertFalse(result);
    }

    @Test
    public void shouldNotMakeMoveWhenAllCellsAreMarked() throws Exception {
        int[][] board = game.getBoard();

        for (int[] row : board) {
            for(int i=0; i < board.length - 1; i++) {
                row[i] = MarkType.PLAYER;
            }
        }

        boolean result = playerService.makeMove(game, PLAYER, FIRST_COlUMN_NUMBER);
        assertFalse(result);
    }


    @Test
    public void shouldNotMakeMoveWithDrawStatus() throws Exception {
        game.setState(Game.State.DRAW);
        boolean result = playerService.makeMove(game, PLAYER, FIRST_COlUMN_NUMBER);
        assertFalse(result);
    }


    @After
    public void tearDown() throws Exception {
        gameRepository.deleteAll();

    }
}