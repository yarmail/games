package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;
import java.util.Optional;

public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * boolean move - может двигаться или нет - проверка
     * move используется в классе Chess, когда отпущена кнопка
     * if (index == -1)(usr findBy) фигура не найдена в созданном массиве фигур
     * значит туда можно ходить - клетка пустая
     *
     * Если так ходить нельзя (правила указваются в объекте фигур методом way(),
     * то в Cell[] steps = new Cell[0]
     *
     * если так ходить можно Cell[] steps = клетке dest
     *
     * Сами правила хода задаются в реализации метода .way каждым объектом фигурой
     *
     *
     * @param source исходная клетка
     * @param dest конечная клетка
     * @return yes, no
     */
    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException("Фигура не найдена");
        }

        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (steps.length == 0) {
                throw new ImpossibleMoveException("Так ходить нельзя");
            }
            for (Cell step: steps) {
                if (findBy(step) != -1) {
                    throw new OccupiedWayException("Путь занят");
                }
            }
            if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }


    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * (Используется только для метода move() класса Logic)
     *  1) метод для поиска индекса в массиве фигур
     *  2) да этот метод можно использовать для проверки свободного пути и проверки того, что метод move() вызван для существущей фигуры
     *  Получает клетку (например A1)
     *
     * @param cell
     * @return Если фигура есть - возвращается её индекс
     *  Если фигуры на этой клетке нет, то (-1)
     */
    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    @Override
    public String toString() {
        return "Logic{" +
                "figures=" + Arrays.toString(this.figures) +
                '}';
    }
}
