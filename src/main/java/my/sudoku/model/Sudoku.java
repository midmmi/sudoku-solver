package my.sudoku.model;

/**
 * Created by M4 on 16/01/2017.
 */
public interface Sudoku {

    CellSequence getRow(int y);

    CellSequence getColumn(int x);

    CellSequence getSquare(int x, int y);

    CellSequence getSquare(int i);

    boolean isSolved();

    boolean isDefined(int x, int y);

    void setValue(int x, int y, int value);
}
