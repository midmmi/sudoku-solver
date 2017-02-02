package my.sudoku.model;

/**
 * Created by M4 on 16/01/2017.
 */
public interface Cell {
    boolean isDefined();

    int getValue();

    void setValue(int value);
}
