package com.testtask.config;

import com.testtask.domain.Board;
import com.testtask.service.ConfigurationService;
import com.testtask.service.GameStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Liza on 25-Jul-16.
 */
//@ComponentScan("com.testtask.domain")
@Configuration
//@ComponentScan{"com.testtask.domain"}
public class AppConfig {

    @Bean
    public ConfigurationService configurationService() {
        return new ConfigurationService();
    }

    @Bean
    public Board board() {
        int size = configurationService().getBoardSize();
        return new Board(new int[size][size]);
    }
}
