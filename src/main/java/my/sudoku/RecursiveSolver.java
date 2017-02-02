package my.sudoku;

import my.sudoku.model.CellSequence;
import my.sudoku.model.Sudoku;
import my.sudoku.model.impl.SudokuImpl;
import my.sudoku.util.SudokuUtils;

import java.util.Set;

/**
 * Created by M4 on 19/01/2017.
 *
 * This class is not thread safe. To make it thread safe just store local sudoku in ThreadLocal
 */
public class RecursiveSolver implements SudokuSolver {
    private SudokuSolver solver;
    private Sudoku sudoku;

    private RecursiveSolver() {
    }

    public RecursiveSolver(SudokuSolver solver) {
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

        solveRecursive(sudoku);

        return this.sudoku;
    }


    private void solveRecursive(Sudoku sudoku) {
        if (this.sudoku != null && this.sudoku.isSolved()) {
            return;
        }

        for (int y = 0; y < 9; y++) {
            CellSequence row = sudoku.getRow(y);
            if (row.isFullyDefined()) {
                continue;
            }

            for (int x = 0; x < 9; x++) {
                if (!row.isDefined(x)) {

                    Set<Integer> possibleValues = SudokuUtils.intersectSets(row.getUndefinedValues(),
                            sudoku.getColumn(x).getUndefinedValues(), sudoku.getSquare(x, y).getUndefinedValues());

                    if (possibleValues.isEmpty()) {
                        return;
                    }

                    for (int pv : possibleValues) {
                        Sudoku copy = new SudokuImpl(sudoku);
                        copy.setValue(x, y, pv);

    /*                    try {
                            solver.solve(copy);
                        } catch (RuntimeException e) {
                            //ignore; wrong resolution
                            continue;
                        }
*/
                        if (copy.isSolved()) {
                            this.sudoku = copy;
                            return;
                        }

                        solveRecursive(copy);
                    }

                    return;
                }
            }
        }
        throw new RuntimeException("Can't solve sudoku: " + sudoku);
    }
}
