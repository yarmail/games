package ru.job4j.chess;

/**
 * Исключение создается если фигура не может пойти
 */
public class ImpossibleMoveException extends RuntimeException {

    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}