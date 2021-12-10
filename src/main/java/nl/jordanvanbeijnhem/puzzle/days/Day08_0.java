package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Pattern;

public class Day08_0 extends Puzzle {

    public Day08_0(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        List<String> lines = getLines();
        int result = 0;
        for (String line : lines) {
            String[] lineSplit = line.split(Pattern.quote("|"));
            String[] outputDigits = lineSplit[1].split(" ");
            for (String outputDigit : outputDigits) {
                if(outputDigit.length() == 2) result++;
                if(outputDigit.length() == 3) result++;
                if(outputDigit.length() == 4) result++;
                if(outputDigit.length() == 7) result++;
            }
        }
        Main.LOGGER.log(Level.INFO, "{0}", result);
    }
}
