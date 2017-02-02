package my.sudoku.model.impl;

import my.sudoku.model.Cell;

/**
 * Created by M4 on 18/01/2017.
 */
public class CellImpl implements Cell {
    private int value = 0;

    public CellImpl() {
    }

    public CellImpl(Cell cell) {
        if (cell.isDefined()) {
            setValue(cell.getValue());
        }
    }

    public CellImpl(int value) {
        this.value = value;
    }

    @Override
    public boolean isDefined() {
        return value != 0;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        if (value < 1 || value > 9) {
            throw new RuntimeException("Incorrect sudoku value: " + value);
        }

        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
