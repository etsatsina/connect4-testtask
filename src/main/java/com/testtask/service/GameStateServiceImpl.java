package com.testtask.service;

import com.testtask.domain.Board;
import com.testtask.domain.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Liza on 24-Jul-16.
 */
@Service
public class GameStateServiceImpl implements GameStateService {

    private final Board board;

    @Autowired
    public GameStateServiceImpl(final Board board) {
        this.board = board;
    }

    @Override
    public void save(Move move) {
        int[][] matrix = board.getBoardMatrix();
        matrix[move.getX()][move.getY()] = 1;
        System.out.println("matrix changed");
    }

    @Override
    public void init() {

    }

    @Override
    public boolean isWin() {
        return false;
    }
}
