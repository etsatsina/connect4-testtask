package com.testtask.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Liza on 25-Jul-16.
 */
@Service
public class ConfigurationService {

    @Value("${app.board.size}")
    private int boardSize;

    public int getBoardSize() {
        return boardSize;
    }
}
