package com.testtask.rest;

import com.testtask.domain.Move;
import com.testtask.service.GameStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Liza on 24-Jul-16.
 */

@RestController
@RequestMapping("/api/")
public class GameStateResource {

    @Autowired
    GameStateService gameStateService;

    @RequestMapping(value = "/move/", method = RequestMethod.POST)
    public void saveMove(@RequestBody Move move) {
        gameStateService.save(move);
        System.out.println("got post request");
    }
}
