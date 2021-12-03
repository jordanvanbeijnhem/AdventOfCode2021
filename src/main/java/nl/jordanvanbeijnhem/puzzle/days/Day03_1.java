package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

public class Day03_1 extends Puzzle {

    public Day03_1(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        List<String> oxygenRatingLines = getLines();
        List<String> CO2RatingLines = getLines();
        int lineLength = oxygenRatingLines.get(0).length(); // All lines should be the same length
        for (int i = 0; i < lineLength; i++) {
            if (oxygenRatingLines.size() == 1 && CO2RatingLines.size() == 1) break;

            int finalI = i;
            if (oxygenRatingLines.size() > 1) {
                char oxygenMostCommonBit = findMostCommonBit(oxygenRatingLines, i);
                oxygenRatingLines = oxygenRatingLines.stream().filter(item -> item.charAt(finalI) == oxygenMostCommonBit).toList();
            }
            if (CO2RatingLines.size() > 1) {
                char CO2LeastCommonBit = findLeastCommonBit(CO2RatingLines, i);
                CO2RatingLines = CO2RatingLines.stream().filter(item -> item.charAt(finalI) == CO2LeastCommonBit).toList();
            }
        }
        int result = Integer.parseInt(oxygenRatingLines.get(0), 2) * Integer.parseInt(CO2RatingLines.get(0), 2);
        Main.LOGGER.log(Level.INFO, "{0}", result);
    }

    private char findMostCommonBit(List<String> lines, int bitPosition) {
        int numberOfZeroBits = 0;
        int numberOfOneBits = 0;
        for (String line : lines) {
            char oneOrZero = line.charAt(bitPosition);
            if (oneOrZero == '0') numberOfZeroBits++;
            if (oneOrZero == '1') numberOfOneBits++;
        }
        if (numberOfZeroBits > numberOfOneBits) return '0';
        return '1';
    }

    private char findLeastCommonBit(List<String> lines, int bitPosition) {
        int numberOfZeroBits = 0;
        int numberOfOneBits = 0;
        for (String line : lines) {
            char oneOrZero = line.charAt(bitPosition);
            if (oneOrZero == '0') numberOfZeroBits++;
            if (oneOrZero == '1') numberOfOneBits++;
        }
        if (numberOfOneBits < numberOfZeroBits) return '1';
        return '0';
    }
}
