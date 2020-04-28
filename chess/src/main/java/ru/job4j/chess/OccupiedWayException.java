package ru.job4j.chess;

/**
 * Исключение создается, если клетка занята
 */
public class OccupiedWayException extends RuntimeException {

    public OccupiedWayException(String msg) {
        super(msg);
    }
}