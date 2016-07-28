package com.testtask.rest;

import com.testtask.Connect4gameApplication;
import com.testtask.domain.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by etsatsina on 28-Jul-16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Connect4gameApplication.class)
@WebAppConfiguration
public class GameStateResourceTest {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    GameRepository gameRepository;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldCreateGame() throws Exception {
        mockMvc.perform(post("/api/game/create")
                .param("playerUsername", "player1")
                .param("opponentUsername", "player2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        gameRepository.deleteAll();
    }

    @Test
    public void shouldtGetCurrentGameSuccessfully() throws Exception {
        mockMvc.perform(post("/api/game/create")
                .param("playerUsername", "player1").param("opponentUsername", "player2"));

        mockMvc.perform(get("/api/game")
                .param("username", "player1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/game")
                .param("username", "player2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        gameRepository.deleteAll();
    }

    @Test
    public void shouldNotReturnCurrentGameToWrongUser() throws Exception {
        mockMvc.perform(get("/api/game")
                .param("username", "player3")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }


    @Test
    public void shouldPlaySuccessfully() throws Exception {
        mockMvc.perform(post("/api/game/create")
                .param("playerUsername", "player1").param("opponentUsername", "player2"));

        mockMvc.perform(post("/api/game/play")
                .param("username", "player1").param("column","1")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        gameRepository.deleteAll();

    }

    @Test
    public void shouldNotPlayByWrongPlayer() throws Exception {
        mockMvc.perform(post("/api/game/play")
                .param("username", "player3").param("column","1")).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotCreateGameWhileCurrentSessionExists() throws Exception {
        mockMvc.perform(post("/api/game/create")
                .param("playerUsername", "player1").param("opponentUsername", "player2"));

        mockMvc.perform(post("/api/game/create")
                .param("playerUsername", "player1").param("opponentUsername", "player3")).andExpect(status().isBadRequest());

        gameRepository.deleteAll();
    }

}