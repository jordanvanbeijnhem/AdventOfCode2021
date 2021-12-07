package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Day06_0 extends Puzzle {

    public Day06_0(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        List<String> lines = getLines();
        List<LanternFish> fish = new ArrayList<>();
        for (String line : lines.get(0).split(",")) {
            fish.add(new LanternFish(Integer.parseInt(line)));
        }

        int days = 256;
        for (int i = 0; i < days; i++) {
            List<LanternFish> newFishSpawnedOnDay = new ArrayList<>();
            for (LanternFish lanternFish : fish) {
                LanternFish newFish = lanternFish.liveDay();
                if (newFish != null) {
                    newFishSpawnedOnDay.add(newFish);
                }
            }
            fish.addAll(newFishSpawnedOnDay);
        }
        Main.LOGGER.log(Level.INFO, "{0}", fish.size());
    }

    private class LanternFish {

        private static final int NEW_FISH_INTERNAL_COUNTER = 8;
        private static final int RESET_FISH_INTERNAL_COUNTER = 6;
        private int internalCounter;

        public LanternFish(int internalCounter) {
            this.internalCounter = internalCounter;
        }

        public LanternFish liveDay() {
            if (internalCounter == 0) {
                internalCounter = RESET_FISH_INTERNAL_COUNTER;
                return new LanternFish(NEW_FISH_INTERNAL_COUNTER);
            } else {
                internalCounter--;
            }
            return null;
        }
    }
}
