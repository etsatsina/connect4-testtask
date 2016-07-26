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

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/play", method = RequestMethod.POST)
    public ResponseEntity<Game> saveMove(@RequestParam String playerUsername, @RequestParam int column) {
        Game resultGame =  playerService.makeMove(playerUsername, column);
        return new ResponseEntity<>(resultGame, HttpStatus.OK);
    }

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Game> getGame(@RequestParam String username){

    }

    @RequestMapping(value = "/game/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Game> newGame(@RequestParam String username){
        ResponseEntity<Game> response = new ResponseEntity<>(gameService.createGame(username), HttpStatus.OK);

        return response;
    }
}
