package my.sudoku;

import my.sudoku.model.CellSequence;
import my.sudoku.model.Sudoku;

import java.util.Set;

/**
 * Created by M4 on 19/01/2017.
 */
public class SequenceSolver implements SudokuSolver {
    private SudokuSolver solver;

    public SequenceSolver() {
    }

    public SequenceSolver(SudokuSolver solver) {
        this.solver = solver;
    }

    @Override
    public Sudoku solve(Sudoku sudoku) {
        if (solver != null) {
            solver.solve(sudoku);
        }

        if (sudoku.isSolved()) {
            return sudoku;
        }

        for (int y = 0; y < 9; y++) {
            CellSequence row = sudoku.getRow(y);
            Set<Integer> undefinedValues = row.getUndefinedValues();
            int index = -1;
            boolean isAmbigues = false;
            for (int val : undefinedValues) {
                for (int x = 0; x < 9; x++) {
                    if (row.isDefined(x)) {
                        continue;
                    }

                    CellSequence column = sudoku.getColumn(x);
                    if (column.findByValue(val) != null) {
                        continue;
                    }

                    CellSequence square = sudoku.getSquare(x, y);
                    if (square.findByValue(val) != null) {
                        continue;
                    }

                    if (index != -1) {
                        isAmbigues = true;
                        break;
                    }

                    index = x;
                }

                if (!isAmbigues && index != -1) {
                    System.out.println(String.format("Sequence solver setting value: %d at x=%d, y=%d", val, index, y));
                    sudoku.setValue(index, y, val);
                }
            }
        }
        return sudoku;
    }
}
