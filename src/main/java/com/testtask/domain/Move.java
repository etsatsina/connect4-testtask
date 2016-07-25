package com.testtask.domain;

import lombok.NoArgsConstructor;

/**
 * Created by Liza on 24-Jul-16.
 */
@NoArgsConstructor
public final class Move {

    private int x;

    private int y;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
