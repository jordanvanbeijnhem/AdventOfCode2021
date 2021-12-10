package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

public class Day09_1 extends Puzzle {

    public Day09_1(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        List<String> lines = getLines();
        List<List<Integer>> positions = new ArrayList<>();
        List<Integer> basinSizes = new ArrayList<>();
        int result = 0;
        for (int i = 0; i < lines.size(); i++) {
            positions.add(new ArrayList<>());
            for (int y = 0; y < lines.get(i).length(); y++) {
                int currentPosition = Integer.parseInt("" + lines.get(i).charAt(y));
                positions.get(i).add(currentPosition);

                boolean isLowest = true;
                if (i - 1 >= 0) {
                    int upPosition = Integer.parseInt("" + lines.get(i - 1).charAt(y));
                    if (currentPosition >= upPosition) isLowest = false;
                }
                if (y + 1 < lines.get(i).length()) {
                    int rightPosition = Integer.parseInt("" + lines.get(i).charAt(y + 1));
                    if (currentPosition >= rightPosition) isLowest = false;
                }
                if (i + 1 < lines.size()) {
                    int downPosition = Integer.parseInt("" + lines.get(i + 1).charAt(y));
                    if (currentPosition >= downPosition) isLowest = false;
                }
                if (y - 1 >= 0) {
                    int leftPosition = Integer.parseInt("" + lines.get(i).charAt(y - 1));
                    if (currentPosition >= leftPosition) isLowest = false;
                }

                if (isLowest) basinSizes.add(findBasinSize(lines, i, y, new ArrayList<>()));
            }
        }
        Collections.sort(basinSizes);
        Collections.reverse(basinSizes);
        basinSizes.forEach(s -> System.out.println(s));
        Main.LOGGER.log(Level.INFO, "{0}", result);
    }

    private int findBasinSize(List<String> lines, int i, int y, List<String> checkedPositions) {
        checkedPositions.add(y + "," + i);
        int currentPosition = Integer.parseInt("" + lines.get(i).charAt(y));
        int size = 1;
        Integer upPosition = null;
        Integer rightPosition = null;
        Integer downPosition = null;
        Integer leftPosition = null;
        if (i - 1 >= 0) upPosition = Integer.parseInt("" + lines.get(i - 1).charAt(y));
        if (y + 1 < lines.get(i).length()) rightPosition = Integer.parseInt("" + lines.get(i).charAt(y + 1));
        if (i + 1 < lines.size()) downPosition = Integer.parseInt("" + lines.get(i + 1).charAt(y));
        if (y - 1 >= 0) leftPosition = Integer.parseInt("" + lines.get(i).charAt(y - 1));

        if (upPosition == null && rightPosition == null && downPosition == null && leftPosition == null) {
            return size;
        }

        if (upPosition != null && upPosition < 9 && upPosition > currentPosition && !checkedPositions.contains(y + "," + (i - 1)))
            size += findBasinSize(lines, i - 1, y, checkedPositions);
        if (rightPosition != null && rightPosition < 9 && rightPosition > currentPosition && !checkedPositions.contains((y + 1) + "," + i))
            size += findBasinSize(lines, i, y + 1, checkedPositions);
        if (downPosition != null && downPosition < 9 && downPosition > currentPosition && !checkedPositions.contains(y + "," + (i + 1)))
            size += findBasinSize(lines, i + 1, y, checkedPositions);
        if (leftPosition != null && leftPosition < 9 && leftPosition > currentPosition && !checkedPositions.contains((y - 1) + "," + i))
            size += findBasinSize(lines, i, y - 1, checkedPositions);
        return size;
    }
}
