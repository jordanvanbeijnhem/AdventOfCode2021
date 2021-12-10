package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Day09_0 extends Puzzle {

    public Day09_0(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        List<String> lines = getLines();
        List<List<Integer>> positions = new ArrayList<>();
        int result = 0;
        for (int i = 0; i < lines.size(); i++) {
            positions.add(new ArrayList<>());
            for (int y = 0; y < lines.get(i).length(); y++) {
                int currentPosition = Integer.parseInt("" + lines.get(i).charAt(y));
                positions.get(i).add(currentPosition);

                boolean isLowest = true;
                if (i - 1 >= 0) {
                    int upPosition =  Integer.parseInt("" + lines.get(i - 1).charAt(y));
                    if (currentPosition >= upPosition) isLowest = false;
                }
                if (y + 1 < lines.get(i).length()) {
                    int rightPosition = Integer.parseInt("" + lines.get(i).charAt(y + 1));
                    if (currentPosition >= rightPosition) isLowest = false;
                }
                if (i + 1 < lines.size()) {
                    int downPosition =  Integer.parseInt("" + lines.get(i + 1).charAt(y));
                    if (currentPosition >= downPosition) isLowest = false;
                }
                if (y - 1 >= 0) {
                    int leftPosition = Integer.parseInt("" + lines.get(i).charAt(y - 1));
                    if (currentPosition >= leftPosition) isLowest = false;
                }

                if (isLowest) result += currentPosition + 1;
            }
        }
        Main.LOGGER.log(Level.INFO, "{0}", result);
    }
}
