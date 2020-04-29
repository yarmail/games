package ru.job4j.puzzle;

import ru.job4j.puzzle.firuges.Cell;
import ru.job4j.puzzle.firuges.Figure;

import java.util.Arrays;

public class Logic {
    private final int size;
    private final Figure[] figures;
    private int index = 0;

    public Logic(int size) {
        this.size = size;
        this.figures = new Figure[size * size];
    }

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (this.isFree(steps)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }

    public boolean isFree(Cell ... cells) {
        boolean result = cells.length > 0;
        for (Cell cell : cells) {
            if (this.findBy(cell) != -1) {
               result = false;
               break;
            }
        }
        return result;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

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

     /**
     * Нужно проверить что в массиве единицы образуют
      * ряд по вертикали и горизонтали нужно размера.
     * It is necessary to check that in the array the
      * units form a row vertically and horizontally of
      * the necessary size.
     * @return true or false
     */

    public boolean isWin() {
        int[][] table = this.convert();

        boolean horizontally = false;
        boolean vertical = false;
        boolean result = false;

        for (int i = 0; i < table.length; i++) {
            // если есть единица в первой горизонтали
            if (table[0][i] == 1) {
                vertical = true;
                // проверяем вертикаль
                for (int j = 0; j < table.length; j++) {
                    if (table[j][i] != table[0][i]) {
                        vertical = false;
                    }
                }
                break;
            } else if (table[i][0] == 1) {
                horizontally = true;
                // проверяем горизонталь
                for (int j = 0; j < table.length; j++) {
                    if (table[i][j] != table[i][0]) {
                        horizontally = false;
                    }
                }
                break;
            }
        }
        if (vertical | horizontally) {
            result = true;
        }
        return result;
    }


    public int[][] convert() {
        int[][] table = new int[this.size][this.size];
        for (int row = 0; row != table.length; row++) {
            for (int cell = 0; cell != table.length; cell++) {
                int position = this.findBy(new Cell(row, cell));
                if (position != -1 && this.figures[position].movable()) {
                    table[row][cell] = 1;
                }
            }
        }
        return table;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.convert());
    }
}
