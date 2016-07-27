package com.testtask.config;

import com.testtask.domain.Game;
import com.testtask.service.ConfigurationService;
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
}
