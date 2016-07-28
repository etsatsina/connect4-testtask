package com.testtask.service;

import com.testtask.domain.Game;
import com.testtask.domain.MarkType;
import org.springframework.stereotype.Service;

/**
 * Created by Liza on 27-Jul-16.
 */
@Service
public class WinnerCheckService {

    public void checkAndUpdateWinner(Game game, int column) {
        int winner;
        int[][] board = game.getBoard();

        winner = checkForWinnerHorizontal(board);
        if (winner != MarkType.NONE) {
            updateWinner(game, winner);
        }

        winner = checkForWinnerVertical(board, column);
        if (winner != MarkType.NONE) {
            updateWinner(game, winner);
        }

        winner = checkForWinnerDiagonals(board);
        if (winner != MarkType.NONE) {
            updateWinner(game, winner);
        }

        if (checkForDraw(board)) {
            game.setState(Game.State.DRAW);
        }
    }

    private void updateWinner(Game game, int winner) {
        if (winner == MarkType.PLAYER) {
            game.setState(Game.State.PLAYER_WINS);
        }
        if (winner == MarkType.OPPONENT) {
            game.setState(Game.State.OPPONENT_WINS);
        }
    }

    private boolean checkForDraw(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == MarkType.NONE) {
                    return false;
                }
            }
        }

        return true;
    }

    private int checkForWinnerHorizontal(int[][] board) {
        for (int[] row : board) {
            for (int column = 0; column < row.length - 3; column++) {

                if (row[column] == row[column + 1]
                        && row[column] == row[column + 2]
                        && row[column] == row[column + 3]
                        && row[column] == MarkType.NONE) {
                    return row[column];
                }
            }
        }

        return MarkType.NONE;
    }

    private int checkForWinnerVertical(int[][] board, int targetColumn) {
        for (int row = 0; row < board[0].length - 3; row++) {
            if (board[row][targetColumn] == board[row + 1][targetColumn]
                    && board[row][targetColumn] == board[row + 2][targetColumn]
                    && board[row][targetColumn] == board[row + 3][targetColumn]
                    && board[row][targetColumn] != MarkType.NONE) {
                return board[row][targetColumn];
            }
        }

        return MarkType.NONE;
    }


    private int checkForWinnerDiagonals(int[][] board) {
        for (int row = 0; row < board.length - 3; row++) {
            for (int column = 0; column < board[0].length - 3; column++) {

                if (board[row][column] == board[row + 1][column + 1]
                        && board[row][column] == board[row + 2][column + 2]
                        && board[row][column] == board[row + 3][column + 3]
                        && board[row][column] != MarkType.NONE) {
                            return board[row][column];
                    }
                }
            }

        for (int row = 0; row < board.length - 3; row++) {
            for (int column = 3; column < board[0].length; column++) {

                if (board[row][column] == board[row + 1][column - 1]
                        && board[row][column] == board[row + 2][column - 2]
                        && board[row][column] == board[row + 3][column - 3]
                        && board[row][column] != MarkType.NONE) {
                    return board[row][column];
                }
            }
        }

        return MarkType.NONE;
    }
}
