package com.testtask.rest;

import com.testtask.domain.Game;
import com.testtask.service.GameService;
import com.testtask.service.PlayerService;
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
        Game retrievedGame = gameService.retrieveGame(username);
        ResponseEntity<Game> response;

        if (retrievedGame == null){
            response = new ResponseEntity<>(retrievedGame, HttpStatus.BAD_REQUEST);
        } else {
            Game resultGame = gameService.saveMove(retrievedGame, username, column);
            response = new ResponseEntity<>(resultGame, HttpStatus.OK);
        }
        return response;
    }

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Game> getGame(@RequestParam String username){
        ResponseEntity<Game> response;
        Game retrievedGame = gameService.retrieveGame(username);

        if (retrievedGame == null){
            response = new ResponseEntity<>(retrievedGame, HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity<>(retrievedGame, HttpStatus.OK);
        }

        return response;
    }

    @RequestMapping(value = "/game/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Game> newGame(@RequestParam String playerUsername, @RequestParam String opponentUsername){
        ResponseEntity<Game> response = new ResponseEntity<>(gameService.createGame(playerUsername, opponentUsername), HttpStatus.OK);

        return response;
    }
}
