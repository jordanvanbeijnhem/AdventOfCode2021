package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

public class Day02_1 extends Puzzle {

    public Day02_1(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        Position position = new Position(0, 0, 0);
        List<String> lines = getLines();
        for (String line : lines) {
            String[] lineSplit = line.split(" ");
            try {
                position.getClass().getMethod(lineSplit[0], int.class).invoke(position, Integer.parseInt(lineSplit[1]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Main.LOGGER.log(Level.INFO, "{0}", position.getX() * position.getY());
    }

    private class Position {
        private int x;
        private int y;
        private int aim;

        public Position(int x, int y, int aim) {
            this.x = x;
            this.y = y;
            this.aim = aim;
        }

        public void up(int steps) {
            this.aim -= steps;
        }

        public void forward(int steps) {
            this.x += steps;
            this.y += steps * this.aim;
        }

        public void down(int steps) {
            this.aim += steps;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}