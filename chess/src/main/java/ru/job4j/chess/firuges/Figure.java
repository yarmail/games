package ru.job4j.chess.firuges;

public interface Figure {
    /**
     * Позиция фигуры
     * @return
     */
    Cell position();

    /**
     * Метод, который проверяет, что фигура может так двигаться
     * (Cell source, cell dest) - начальная и конечная точка
     * если фигура может так пойти, то мы возвращаем (конечную клетку)
     * @param source
     * @param dest
     * @return
     */
    Cell[] way(Cell source, Cell dest);

    /**
     * Загрузка  изображений
     * @return
     */
    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );
    }
    /**
     * Формальное перемещение фигуры
     * @param dest
     * @return
     */
    Figure copy(Cell dest);
}
