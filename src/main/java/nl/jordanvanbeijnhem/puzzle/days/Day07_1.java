package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.IntStream;

public class Day07_1 extends Puzzle {

    public Day07_1(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        List<String> lines = getLines();
        List<Integer> horizontalPositions = Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).toList();
        int minPosition = horizontalPositions.get(0);
        int maxPosition = horizontalPositions.get(0);
        for (Integer horizontalPosition : horizontalPositions) {
            if (horizontalPosition < minPosition) minPosition = horizontalPosition;
            if (horizontalPosition > maxPosition) maxPosition = horizontalPosition;
        }

        List<Integer> fuelRequiredData = new ArrayList<>();
        IntStream.range(minPosition, maxPosition + 1).forEach((int positionToMoveTo) -> {
            int totalFuelRequired = 0;
            for (Integer horizontalPosition : horizontalPositions) {
                int stepsRequired = Math.abs(horizontalPosition - positionToMoveTo);
                int fuelRequired = 0;
                for (int i = 0; i < stepsRequired; i++) {
                    fuelRequired += i + 1;
                }
                totalFuelRequired += fuelRequired;
            }
            fuelRequiredData.add(totalFuelRequired);
        });
        Collections.sort(fuelRequiredData);
        Main.LOGGER.log(Level.INFO, "{0}", fuelRequiredData.get(0));
    }
}
