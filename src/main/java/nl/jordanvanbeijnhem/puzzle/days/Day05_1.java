package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Day05_1 extends Puzzle {

    private static final int DIAGRAM_WIDTH = 1000;
    private static final int DIAGRAM_HEIGHT = 1000;


    public Day05_1(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        List<List<Integer>> diagram = createDiagram();
        int numberOfDangerousPostions = 0;
        List<String> lines = getLines();
        for (String line : lines) {
            String[] coordinates = line.split(" -> ");
            Position startPosition = coordinateToPosition(coordinates[0]);
            Position endPosition = coordinateToPosition(coordinates[1]);
            List<Position> positionRange = findPostionRange(startPosition, endPosition);

            for (Position position : positionRange) {
                int positionValue = diagram.get(position.y).get(position.x);
                if (positionValue == 1) numberOfDangerousPostions++;
                diagram.get(position.y).set(position.x, positionValue + 1);
            }
        }
        Main.LOGGER.log(Level.INFO, "{0}", numberOfDangerousPostions);
    }

    private List<List<Integer>> createDiagram() {
        List<List<Integer>> diagram = new ArrayList<>();
        for (int i = 0; i < DIAGRAM_HEIGHT; i++) {
            List<Integer> row = new ArrayList<>();
            for (int i1 = 0; i1 < DIAGRAM_WIDTH; i1++) {
                row.add(0);
            }
            diagram.add(row);
        }
        return diagram;
    }

    private Position coordinateToPosition(String coordinate) {
        String[] xAndY = coordinate.split(",");
        return new Position(Integer.parseInt(xAndY[0]), Integer.parseInt(xAndY[1]));
    }

    private List<Position> findPostionRange(Position startPosition, Position endPosition) {
        List<Position> positionRange = new ArrayList<>();
        boolean reverseX = (endPosition.x - startPosition.x) < 0;
        int slope = endPosition.x - startPosition.x == 0 ? 0 : (endPosition.y - startPosition.y) / (endPosition.x - startPosition.x);

        if (slope != 0) {
            for (int x = startPosition.x; reverseX ? x >= endPosition.x : x <= endPosition.x; ) {
                int deltaX = x - startPosition.x;
                positionRange.add(new Position(x, startPosition.y + (deltaX * slope)));
                if (reverseX) x--;
                else x++;
            }
        } else {
            boolean reverseY = (endPosition.y - startPosition.y) < 0;

            for (int x = startPosition.x; reverseX ? x >= endPosition.x : x <= endPosition.x; ) {
                for (int y = startPosition.y; reverseY ? y >= endPosition.y : y <= endPosition.y; ) {
                    positionRange.add(new Position(x, y));
                    if (reverseY) y--;
                    else y++;
                }
                if (reverseX) x--;
                else x++;
            }
        }
        return positionRange;
    }

    private class Position {

        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + "," + y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
