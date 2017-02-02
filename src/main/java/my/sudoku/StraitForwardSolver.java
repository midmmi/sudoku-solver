package my.sudoku;

import my.sudoku.model.Sudoku;
import my.sudoku.util.SudokuUtils;

import java.util.Set;

/**
 * Created by M4 on 16/01/2017.
 */
public class StraitForwardSolver implements SudokuSolver {
    @Override
    public Sudoku solve(Sudoku sudoku) {
        int loopNumber = 0;

        while (!sudoku.isSolved()) {
            int setValuesInThisLoop = 0;

            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    if (sudoku.isDefined(x, y)){
                        continue;
                    }

                    Set<Integer> intersect = SudokuUtils.intersectSets(sudoku.getRow(y).getUndefinedValues(),
                            sudoku.getColumn(x).getUndefinedValues(), sudoku.getSquare(x, y).getUndefinedValues());

                    if (intersect.isEmpty() || intersect.size() > 1) {
                        continue;
                    }

                    sudoku.setValue(x, y, intersect.iterator().next());
                    ++setValuesInThisLoop;
                }
            }
            ++loopNumber;
            System.out.println("Values set in this loop=" + setValuesInThisLoop);

            if (setValuesInThisLoop == 0) {
                System.out.println("No values set in this loop, exiting...");
                break;
            }
        }

        System.out.println("Sudoku is solved=" + sudoku.isSolved() + "; Loops done: " + loopNumber);
        return sudoku;
    }

}
