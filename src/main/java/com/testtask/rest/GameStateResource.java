package com.testtask.rest;

import com.testtask.domain.Game;
import com.testtask.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Liza on 24-Jul-16.
 */

@RestController
@RequestMapping("api/game")
public class GameStateResource {

    @Autowired
    GameService gameService;

    @RequestMapping(value = "/play", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Game> saveMove(@RequestParam String username, @RequestParam int column) {
        Game retrievedGame = gameService.retrieveGame(username, Game.State.CONTINUES);
        ResponseEntity<Game> response;

        if (retrievedGame == null){
            response = new ResponseEntity<>(retrievedGame, HttpStatus.BAD_REQUEST);
        } else {
            Game resultGame = gameService.saveMove(retrievedGame, username, column);
            response = new ResponseEntity<>(resultGame, HttpStatus.OK);
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Game> getCurrentGame(@RequestParam String username){
        ResponseEntity<Game> response;
        Game retrievedGame = gameService.retrieveGame(username, Game.State.CONTINUES);

        if (retrievedGame == null){
            response = new ResponseEntity<>(retrievedGame, HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity<>(retrievedGame, HttpStatus.OK);
        }

       return response;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Game> newGame(@RequestParam String playerUsername, @RequestParam String opponentUsername){
        Game retrievedGameForPlayer = gameService.retrieveGame(playerUsername, Game.State.CONTINUES);
        Game retrievedGameForOpponent = gameService.retrieveGame(opponentUsername, Game.State.CONTINUES);
        Game createdGame = null;

        if (retrievedGameForPlayer == null && retrievedGameForOpponent == null){
            createdGame = gameService.createGame(playerUsername, opponentUsername);
            return new ResponseEntity<>(createdGame, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(createdGame, HttpStatus.BAD_REQUEST);
        }

    }
}
