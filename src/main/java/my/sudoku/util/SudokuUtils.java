package my.sudoku.util;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by M4 on 19/01/2017.
 */
public class SudokuUtils {

    private SudokuUtils() {}

    public static Set<Integer> intersectSets(Set<Integer> sa, Set<Integer> sb, Set<Integer> sc) {
        Set<Integer> res = new HashSet<>(9);

        res.addAll(sa.stream().filter(a -> sb.contains(a) && sc.contains(a)).collect(Collectors.toList()));

        res.addAll(sb.stream().filter(b -> sa.contains(b) && sc.contains(b)).collect(Collectors.toList()));

        res.addAll(sc.stream().filter(c -> sa.contains(c) && sb.contains(c)).collect(Collectors.toList()));

//        System.out.println("Merge sets for: a=" + sa + "; b=" + sb + "; c=" + sc + ": result=" + res);

        return res;
    }
}
