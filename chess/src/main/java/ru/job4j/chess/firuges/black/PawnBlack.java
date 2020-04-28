package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * Пешка черная
 */
public class PawnBlack implements Figure {
    private final Cell position;

    /**
     * принимает и сохраняет позицию, переданую извне типа Cell
     * @param position
     */
    public PawnBlack(final Cell position) {
        this.position = position;
    }

    /**
     * @return Возвращает свою текущую позицию типа Cell
     */
    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * This method sets the rules for the pawn.
     * Данный метод задает правила хода пешки
     * @param source
     * @param dest
     * @return новое местоположение dest если правила выполяняются
     * @return Cell[0], если правила не выполняются
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if (source.y == dest.y + 1 && source.x == dest.x) {
            steps = new Cell[] { dest };
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
