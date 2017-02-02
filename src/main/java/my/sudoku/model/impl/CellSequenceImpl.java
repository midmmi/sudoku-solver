package my.sudoku.model.impl;

import my.sudoku.model.Cell;
import my.sudoku.model.CellSequence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by M4 on 16/01/2017.
 */
public class CellSequenceImpl implements CellSequence {
    private final List<Cell> values = new ArrayList<>(9);

    public CellSequenceImpl(int[] initValues) {
        for (int i = 0; i < 9; i++) {
            values.add(new CellImpl(initValues[i]));
        }
    }

    public CellSequenceImpl(CellSequence cellSequence) {
        for (int i = 0; i < 9; i++) {
            values.add(new CellImpl(cellSequence.getCell(i)));
        }
    }

    @Override
    public boolean isFullyDefined() {
        for(Cell c : values) {
            if (!c.isDefined()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Cell findByValue(int value) {
        for(Cell c : values) {
            if (c.getValue() == value) {
                return c;
            }
        }

        return null;
    }

    @Override
    public Cell getCell(int index) {
        return values.get(index);
    }

    @Override
    public void setValue(Cell value, int index) {
        if(values.contains(value)) {
            throw new RuntimeException("Setting duplication value" + value + "; index=" + index + "; " + toString());
        }
        Cell oldVal = values.set(index, value);
        if (oldVal.isDefined()) {
            throw new RuntimeException("Setting not empty value: value=" + value + "; index=" + index + "; " + toString());
        }
    }

    @Override
    public Set<Integer> getUndefinedValues() {
        Set<Integer> res = new HashSet<>(9);
        for (int i = 1; i < 10; i++) {
            if (findByValue(i) == null) {
                res.add(i);
            }
        }

        return res;
    }

    @Override
    public boolean isDefined(int index) {
        return getCell(index).isDefined();
    }

    @Override
    public String toString() {
        return "CellSequenceImpl{" +
                "values=" + values +
                '}';
    }
}
