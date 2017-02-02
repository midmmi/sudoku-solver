package my.sudoku.model.impl;

import my.sudoku.model.Cell;
import my.sudoku.model.CellSequence;
import my.sudoku.model.Sudoku;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by M4 on 16/01/2017.
 */
public class SudokuImpl implements Sudoku {
    private final int[][] source;
    private final Map<Integer, CellSequence> rows = new HashMap<>(9);
    private final Map<Integer, CellSequence> columns = new HashMap<>(9);
    private final Map<Integer, CellSequence> squares = new HashMap<>(9);

    public SudokuImpl(int[][] values) {
        source = values.clone();
    }

    public SudokuImpl(Sudoku sudoku) {
        source = new int[0][0];

        for (int i = 0; i < 9; i++) {
            rows.put(i, new CellSequenceImpl(sudoku.getRow(i)));
            columns.put(i, new CellSequenceImpl(sudoku.getColumn(i)));
            squares.put(i, new CellSequenceImpl(sudoku.getSquare(i)));
        }
    }

    @Override
    public CellSequence getRow(int x) {
        CellSequence res = rows.get(x);

        if (res == null) {
            res = new CellSequenceImpl(source[x]);
            rows.put(x, res);
        }

        return res;
    }

    @Override
    public CellSequence getColumn(int y) {
        CellSequence res = columns.get(y);

        if (res == null) {
            int[] arr = new int[9];

            for (int i = 0; i < 9; i++) {
                arr[i] = source[i][y];
            }

            res = new CellSequenceImpl(arr);
            columns.put(y, res);
        }

        return res;
    }

    @Override
    public CellSequence getSquare(int x, int y) {
        int index = (x / 3) + (y / 3) * 3;
        CellSequence res = squares.get(index);

        if (res == null) {
            int[] arr = new int[9];
            int xDelta = (x / 3) * 3;
            int yDelta = (y / 3) * 3;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    arr[i*3 + j] = source[i+yDelta][j+xDelta];
                }
            }

            res = new CellSequenceImpl(arr);
            squares.put(index, res);
        }

        return res;
    }

    @Override
    public CellSequence getSquare(int i) {
        CellSequence res = squares.get(i);

        if (res == null) {
            throw new NullPointerException("Badly initialized");
        }

        return res;
    }

    @Override
    public boolean isSolved() {
        for (int i = 0; i < 9; i++) {
            if (getRow(i).isFullyDefined() && getColumn(i).isFullyDefined() && getSquare(i).isFullyDefined()) {
                continue;
            }
            return false;
        }

        return true;
    }

    @Override
    public boolean isDefined(int x, int y) {
        return getRow(y).isDefined(x);
    }

    @Override
    public void setValue(int x, int y, int value) {
        Cell cValue = new CellImpl(value);
        getRow(y).setValue(cValue, x);
        getColumn(x).setValue(cValue, y);
        getSquare(x, y).setValue(cValue, (x % 3) + ((y % 3) * 3));
        System.out.println("The value=" + value + " was set at: " + x + ", " + y);
    }

    public String toExtendedString() {
        StringBuffer sb = new StringBuffer("Rows:\n\r");
        for (int i = 0; i < 9; i++) {
            sb.append("#" + i + ": ").append(getRow(i)).append("; \n\r");
        }

        sb.append("Columns:\n\r");
        for (int i = 0; i < 9; i++) {
            sb.append("#" + i + ": ").append(getColumn(i)).append("; \n\r");
        }

        sb.append("Squares:\n\r");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append("#" + (i + j*3) + ": ").append(getSquare(i*3, j*3)).append("; \n\r");
            }
        }

        sb.append("Sudoku solved: " + isSolved() + "\n\r");

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("Rows:\n\r");
        for (int i = 0; i < 9; i++) {
            sb.append("#" + i + ": ").append(getRow(i)).append("; \n\r");
        }

        sb.append("Sudoku solved: " + isSolved() + "\n\r");

        return sb.toString();
    }
}
