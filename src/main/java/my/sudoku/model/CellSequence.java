package my.sudoku.model;

import java.util.Set;

/**
 * Created by M4 on 16/01/2017.
 */
public interface CellSequence {
    boolean isFullyDefined();

    Cell findByValue(int value);

    Cell getCell(int index);

    void setValue(Cell value, int index);

    Set<Integer> getUndefinedValues();

    boolean isDefined(int index);
}
