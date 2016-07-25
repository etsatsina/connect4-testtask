package com.testtask.domain;

import com.testtask.service.ConfigurationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Liza on 24-Jul-16.
 */
@Component
public class Board {

    private int[][] boardMatrix;

    public Board(int[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

    public int getBoardSize() {
        return boardMatrix.length;
    }

    public int[][] getBoardMatrix() {
//        for (int i=0; i< size; i++) {
//            for(int j=0; j<size; j++) {
//                System.out.println(boardMatrix[i][j]);
//            }
//        }
        return boardMatrix;
    }
}
