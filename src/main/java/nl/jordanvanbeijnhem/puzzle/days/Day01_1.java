package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

public class Day01_1 extends Puzzle {

    public Day01_1(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        int numberOfIncreases = 0;
        List<String> lines = getLines();
        for (int i = 0; i < lines.size() - 2; i++) {
            if (i == 0) continue;
            int prevWindowSum = calculateWindowSum(lines, i - 1);
            int currentWindowSum = calculateWindowSum(lines, i);
            if (prevWindowSum < currentWindowSum) {
                numberOfIncreases++;
            }
        }
        Main.LOGGER.log(Level.INFO, "{0}", numberOfIncreases);
    }

    private int calculateWindowSum(List<String> lines, int windowStartIndex) {
        int sum = 0;
        for (int i = windowStartIndex; i < windowStartIndex + 3; i++) {
            sum += Integer.parseInt(lines.get(i));
        }
        return sum;
    }
}
