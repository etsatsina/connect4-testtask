package com.testtask.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

/**
 * Created by etsatsina on 25-Jul-16.
 */
@Document
public class Game {

    @Id
    private String id;

    private int[][] board;

    private State state;

    private String playerUsername;

    private String opponentUsername;

    private HashMap<String, Integer> usernameMarkMap;

    public enum State {
        PLAYER_WINS, OPPONENT_WINS, DRAW, CONTINUES
    }

    public Game(int[][] board, State state, String playerUsername, String opponentUsername) {
        this.board = board;
        this.state = state;
        this.playerUsername = playerUsername;
        this.opponentUsername = opponentUsername;
    }

    public Game() {
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

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public void setOpponentUsername(String opponentUsername) {
        this.opponentUsername = opponentUsername;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public String getOpponentUsername() {
        return opponentUsername;
    }

    public void setUsernameMarkMap(HashMap<String, Integer> usernameMarkMap) {
        this.usernameMarkMap = usernameMarkMap;
    }

    public HashMap<String, Integer> getUsernameMarkMap() {
        return usernameMarkMap;
    }

    public void setId(String uuid) {
        this.id = uuid;
    }
}


