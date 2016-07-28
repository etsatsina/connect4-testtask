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
public class WinnerCheckServiceTest {

    public static final String PLAYER = "player1";
    public static final String OPPONENT = "player2";
    
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
    public void shouldCheckForWinnerHorizontalPlayerWin() throws Exception {
        gameService.saveMove(game, PLAYER, 1);
        gameService.saveMove(game, PLAYER, 2);
        gameService.saveMove(game, PLAYER, 3);
        gameService.saveMove(game, PLAYER, 4);
        assertEquals(Game.State.PLAYER_WINS, game.getState());
    }

    @Test
    public void shouldCheckForWinnerHorizontalOpponentWin() throws Exception {
        gameService.saveMove(game, OPPONENT, 1);
        gameService.saveMove(game, OPPONENT, 2);
        gameService.saveMove(game, OPPONENT, 3);
        gameService.saveMove(game, OPPONENT, 4);
        assertEquals(Game.State.OPPONENT_WINS, game.getState());
    }

    @Test
    public void shouldCheckForWinnerVerticalOpponentWins() throws Exception {
        gameService.saveMove(game, OPPONENT, 1);
        gameService.saveMove(game, OPPONENT, 1);
        gameService.saveMove(game, OPPONENT, 1);
        gameService.saveMove(game, OPPONENT, 1);
        assertEquals(Game.State.OPPONENT_WINS, game.getState());
    }

    @Test
    public void shouldCheckForWinnerDiagonalLeftToRightPlayerWins() {
        gameService.saveMove(game, PLAYER, 0);
        
        gameService.saveMove(game, OPPONENT, 1);
        gameService.saveMove(game, PLAYER, 1);
        
        gameService.saveMove(game, OPPONENT, 2);
        gameService.saveMove(game, OPPONENT, 2);
        gameService.saveMove(game, PLAYER, 2);
        
        gameService.saveMove(game, OPPONENT, 3);
        gameService.saveMove(game, OPPONENT, 3);
        gameService.saveMove(game, OPPONENT, 3);
        gameService.saveMove(game, PLAYER, 3);
        
        assertEquals(Game.State.PLAYER_WINS, game.getState());
    }

    @Test
    public void shouldCheckForWinnerDiagonalPlayerWinsRightToLeft() {
        gameService.saveMove(game, OPPONENT, 0);
        gameService.saveMove(game, OPPONENT, 0);
        gameService.saveMove(game, OPPONENT, 0);
        gameService.saveMove(game, PLAYER, 0);

        gameService.saveMove(game, OPPONENT, 1);
        gameService.saveMove(game, OPPONENT, 1);
        gameService.saveMove(game, PLAYER, 1);

        gameService.saveMove(game, OPPONENT, 2);
        gameService.saveMove(game, PLAYER, 2);

        gameService.saveMove(game, PLAYER, 3);
        
        assertEquals(Game.State.PLAYER_WINS, game.getState());
    }

    @After
    public void tearDown() throws Exception {
        gameRepository.deleteAll();    
    
    }
}