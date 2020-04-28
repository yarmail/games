package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Правила хода слона
     * Пока не понятно почему
     * steps[i] = Cell.values()[8 * x + y]
     * возможно подобрано по порядку значений
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if (this.isDiagonal(source, dest)) {
            int signX = source.x > dest.x ? -1 : 1;
            int signY = source.y > dest.y ? -1 : 1;
            int size = Math.abs(source.x - dest.x);
            steps = new Cell[size];
            for (int i = 0; i < size; i++) {
                int x = source.x + (i + 1) * signX;
                int y = source.y + (i + 1) * signY;
                steps[i] = Cell.values()[8 * x + y];
            }
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        return false;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
