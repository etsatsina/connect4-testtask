package com.testtask.config;

import com.testtask.domain.Game;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Liza on 25-Jul-16.
 */
@Configuration
public class AppConfig {

    @Bean
    public ConfigurationService configurationService() {
        return new ConfigurationService();
    }

    @Bean
    public Game game() {
        int columnsNumber = configurationService().getColumnsNumber();
        int rowsNumber = configurationService().getRowsNumber();
        int [][] board = new int[columnsNumber][rowsNumber];
        String opponentLevel = configurationService().getOpponentLevel();

        return new Game(board, Game.GameState.CONTINUES, Game.OpponentLevel.valueOf(opponentLevel));
    }
}
