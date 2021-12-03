package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

public class Day03_0 extends Puzzle {

    public Day03_0(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        List<String> lines = getLines();
        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();
        int lineLength = lines.get(0).length(); // All lines should be the same length
        for (int i = 0; i < lineLength; i++) {
            int numberOfZeroBits = 0;
            int numberOfOneBits = 0;
            for (String line : lines) {
                char oneOrZero = line.charAt(i);
                if (oneOrZero == '0') numberOfZeroBits++;
                if (oneOrZero == '1') numberOfOneBits++;
            }
            if (numberOfZeroBits > numberOfOneBits) {
                gammaRate.append("0");
                epsilonRate.append("1");
            } else {
                gammaRate.append("1");
                epsilonRate.append("0");
            }
        }
        int result = Integer.parseInt(gammaRate.toString(), 2) * Integer.parseInt(epsilonRate.toString(), 2);
        Main.LOGGER.log(Level.INFO, "{0}", result);
    }
}
