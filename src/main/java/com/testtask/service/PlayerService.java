package com.testtask.service;

import com.testtask.domain.Game;
import com.testtask.domain.MarkType;
import org.springframework.stereotype.Service;

/**
 * Created by Liza on 27-Jul-16.
 */
@Service
public class PlayerService {

    public boolean makeMove(Game game, String username, int column) {
        int [][] currentBoardState = game.getBoard();
        boolean successfulMove = false;
        Integer playerMark = game.getUsernameMarkMap().get(username);

        if (isMoveValid(game, column)) {
            return false;
        }

        for (int row = 0; row < game.getRowsNumber() - 1; row++) {
            if (currentBoardState[row][column] == MarkType.NONE) {
                currentBoardState[row][column] = playerMark;
                successfulMove = true;
            }
        }

        if (successfulMove) {
            checkForWinner();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isMoveValid(Game game, int column) {
        int lastRowNumber = game.getRowsNumber() - 1;
        int lastColumnsNumber = game.getColumnsNumber() - 1;

        if (column < 0 || column > lastColumnsNumber) {
            return false;
        }

        if (game.getBoard()[lastRowNumber][column] != MarkType.NONE) {
            return false;
        }

        if (!game.getState().equals(Game.State.CONTINUES)) {
            return false;
        }

        return true;
    }

    private void checkForWinner() {

    }
}
