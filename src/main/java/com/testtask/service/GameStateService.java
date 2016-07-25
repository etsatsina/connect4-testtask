package com.testtask.service;

import com.testtask.domain.Move;

/**
 * Created by Liza on 24-Jul-16.
 */
public interface GameStateService {
    void save(Move move);

    boolean isWin();

    void init();
}

