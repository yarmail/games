package ru.job4j.chess;

/**
 * Исключение создается если фигура не найдена (?)
 */
public class FigureNotFoundException extends RuntimeException {

    public FigureNotFoundException(String msg) {
        super(msg);
    }
}