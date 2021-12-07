package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class Day06_1 extends Puzzle {

    public Day06_1(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        List<String> lines = getLines();
        Map<Integer, BigInteger> fish = new HashMap<>();
        for (String line : lines.get(0).split(",")) {
            int value = Integer.parseInt(line);
            if (!fish.containsKey(value)) fish.put(value, BigInteger.ONE);
            else fish.put(value, fish.get(value).add(BigInteger.ONE));
        }

        int days = 256;
        for (int i = 0; i < days; i++) {
            Map<Integer, BigInteger> newFishSpawnedOnDay = new HashMap<>();
            for (Integer key : fish.keySet()) {
                if (key == 0) {
                    BigInteger newFishOnDayCount = newFishSpawnedOnDay.get(key);
                    if (newFishOnDayCount == null) newFishOnDayCount = BigInteger.ZERO;
                    newFishSpawnedOnDay.put(6, newFishOnDayCount.add(fish.get(key)));
                    newFishSpawnedOnDay.put(8, newFishOnDayCount.add(fish.get(key)));
                } else {
                    BigInteger newFishOnDayCount = newFishSpawnedOnDay.get(key - 1);
                    if (newFishOnDayCount == null) newFishOnDayCount = BigInteger.ZERO;
                    newFishSpawnedOnDay.put(key - 1, newFishOnDayCount.add(fish.get(key)));
                }
            }
            fish = newFishSpawnedOnDay;
        }

        BigInteger sum = BigInteger.ZERO;
        for (BigInteger value : fish.values()) {
            sum = sum.add(value);
        }
        Main.LOGGER.log(Level.INFO, "{0}", sum);
    }
}
