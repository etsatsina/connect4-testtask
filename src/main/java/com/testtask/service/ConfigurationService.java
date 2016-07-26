package com.testtask.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Liza on 25-Jul-16.
 */
@Service
public class ConfigurationService {

    @Value("${app.board.rows_number}")
    private int rowsNumber;

    @Value("${app.board.columns_number}")
    private int columnsNumber;

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getColumnsNumber() {
        return columnsNumber;
    }
}
