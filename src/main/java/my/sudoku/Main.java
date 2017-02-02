package my.sudoku;

import my.sudoku.model.Sudoku;
import my.sudoku.model.impl.SudokuImpl;

/**
 * Created by M4 on 16/01/2017.
 */
public class Main {

    private static int[][] easy_values = { //http://www.websudoku.com/?level=1&set_id=7661795225
            {0, 0, 0, 0, 2, 1, 5, 0, 0},
            {0, 0, 6, 0, 5, 0, 3, 0, 9},
            {5, 0, 0, 3, 0, 0, 8, 0, 2},
            {0, 5, 0, 0, 7, 6, 1, 0, 0},
            {6, 8, 0, 0, 0, 0, 0, 7, 5},
            {0, 0, 1, 2, 4, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 2, 0, 0, 1},
            {1, 0, 9, 0, 6, 0, 2, 0, 0},
            {0, 0, 5, 1, 8, 0, 0, 0, 0}
    };

    //http://www.websudoku.com/?level=2&set_id=5341675681
    private static int[][] medium_values = {
            {0, 0, 8, 0, 6, 0, 0, 0, 4},
            {0, 6, 0, 1, 8, 4, 0, 0, 7},
            {0, 4, 0, 0, 0, 5, 2, 0, 6},
            {0, 0, 0, 0, 0, 8, 6, 5, 0},
            {0, 0, 0, 4, 0, 6, 0, 0, 0},
            {0, 2, 9, 5, 0, 0, 0, 0, 0},
            {5, 0, 6, 9, 0, 0, 0, 7, 0},
            {1, 0, 0, 6, 4, 7, 0, 3, 0},
            {4, 0, 0, 0, 5, 0, 1, 0, 0}
    };

    //http://www.websudoku.com/?level=3&set_id=10204819207
    private static int[][] hard_values = {
            {7, 2, 0, 0, 0, 4, 0, 0, 1},
            {0, 0, 0, 7, 0, 0, 0, 5, 0},
            {0, 9, 0, 1, 5, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 2, 8, 0, 0},
            {3, 0, 0, 0, 0, 0, 0, 0, 2},
            {0, 0, 9, 8, 0, 0, 0, 0, 3},
            {0, 0, 0, 0, 1, 7, 0, 2, 0},
            {0, 7, 0, 0, 0, 6, 0, 0, 0},
            {4, 0, 0, 3, 0, 0, 0, 9, 6}
   };

    //http://www.websudoku.com/?level=4&set_id=10317197650
    private static int[][] evil_values = {
            {7, 0, 0, 0, 0, 8, 0, 0, 0},
            {4, 0, 0, 0, 0, 9, 0, 1, 0},
            {0, 9, 5, 0, 0, 0, 0, 3, 4},
            {0, 3, 0, 8, 0, 4, 0, 0, 0},
            {0, 5, 0, 0, 0, 0, 0, 8, 0},
            {0, 0, 0, 6, 0, 1, 0, 5, 0},
            {5, 7, 0, 0, 0, 0, 9, 6, 0},
            {0, 6, 0, 9, 0, 0, 0, 0, 1},
            {0, 0, 0, 3, 0, 0, 0, 0, 7},
    };


    public static void main(String[] args) {
//        Sudoku sudoku = new SudokuImpl(easy_values);
//        Sudoku sudoku = new SudokuImpl(medium_values);
//        Sudoku sudoku = new SudokuImpl(hard_values);
        Sudoku sudoku = new SudokuImpl(evil_values);

//        SudokuSolver ss = new StraitForwardSolver();
//        SudokuSolver ss = new SequenceSolver(new StraitForwardSolver());
        SudokuSolver ss = new RecursiveSolver(new SequenceSolver(new StraitForwardSolver()));
        sudoku = ss.solve(sudoku);
        System.out.println(sudoku.toString());
//        System.out.println(((SudokuImpl)sudoku).toExtendedString());
    }
}
