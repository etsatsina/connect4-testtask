package com.testtask.domain;

/**
 * Created by Liza on 25-Jul-16.
 */
public class Game {

    public final int NONE = 0;
    public final int PLAYER = 1;
    public final int OPPONENT = -1;

    private int[][] board;

    private State state;

    private String playerUsername;

    private String opponentUsername;

    public enum State {
        PLAYER_WINS, OPPONENT_WINS, DRAW, CONTINUES
    }

    public Game(int[][] board, State state, String playerUsername, String opponentUsername) {
        this.board = board;
        this.state = state;
        this.playerUsername = playerUsername;
        this.opponentUsername = opponentUsername;
    }

    public int getRowsNumber() {
        return board.length;
    }

    public int[][] getBoard() {
        return board;
    }

    public State getState() {
        return state;
    }

    public int getColumnsNumber() {
        if (board.length != 0) {
            return board[0].length;
        }

        return 0;
    }

}
