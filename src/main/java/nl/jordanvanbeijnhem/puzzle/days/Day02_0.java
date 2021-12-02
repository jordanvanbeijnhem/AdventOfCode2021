package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

public class Day02_0 extends Puzzle {

    public Day02_0(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        Position position = new Position(0, 0);
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

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void up(int steps) {
            this.y -= steps;
        }

        public void forward(int steps) {
            this.x += steps;
        }

        public void down(int steps) {
            this.y += steps;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}