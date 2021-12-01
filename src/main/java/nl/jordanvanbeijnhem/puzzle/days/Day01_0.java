package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

public class Day01_0 extends Puzzle {

    public Day01_0(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        int numberOfIncreases = 0;
        List<String> lines = getLines();
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) continue;
            int prevNumber = Integer.parseInt(lines.get(i - 1));
            int currentNumber = Integer.parseInt(lines.get(i));
            if (prevNumber < currentNumber) {
                numberOfIncreases++;
            }
        }
        Main.LOGGER.log(Level.INFO, "{0}", numberOfIncreases);
    }
}
