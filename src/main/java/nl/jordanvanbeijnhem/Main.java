package nl.jordanvanbeijnhem;

import nl.jordanvanbeijnhem.puzzle.PuzzleManager;
import nl.jordanvanbeijnhem.puzzle.days.*;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.setLevel(Level.INFO);

        ClassLoader classLoader = Main.class.getClassLoader();

        PuzzleManager puzzleManager = new PuzzleManager();
        puzzleManager.addPuzzle(new Day01_0("01_0", new File(classLoader.getResource("input/01_0.txt").getFile())));
        puzzleManager.addPuzzle(new Day01_1("01_1", new File(classLoader.getResource("input/01_1.txt").getFile())));
        puzzleManager.addPuzzle(new Day02_0("02_0", new File(classLoader.getResource("input/02_0.txt").getFile())));
        puzzleManager.addPuzzle(new Day02_1("02_1", new File(classLoader.getResource("input/02_1.txt").getFile())));
        puzzleManager.addPuzzle(new Day03_0("03_0", new File(classLoader.getResource("input/03_0.txt").getFile())));
        puzzleManager.addPuzzle(new Day03_1("03_1", new File(classLoader.getResource("input/03_1.txt").getFile())));
        puzzleManager.addPuzzle(new Day04_0("04_0", new File(classLoader.getResource("input/04_0.txt").getFile())));
        puzzleManager.addPuzzle(new Day04_1("04_1", new File(classLoader.getResource("input/04_1.txt").getFile())));
        puzzleManager.addPuzzle(new Day05_0("05_0", new File(classLoader.getResource("input/05_0.txt").getFile())));
        puzzleManager.addPuzzle(new Day05_1("05_1", new File(classLoader.getResource("input/05_1.txt").getFile())));
        puzzleManager.addPuzzle(new Day06_0("06_0", new File(classLoader.getResource("input/06_0.txt").getFile())));
        puzzleManager.addPuzzle(new Day06_1("06_1", new File(classLoader.getResource("input/06_1.txt").getFile())));
        puzzleManager.addPuzzle(new Day07_0("07_0", new File(classLoader.getResource("input/07_0.txt").getFile())));
        puzzleManager.addPuzzle(new Day07_1("07_1", new File(classLoader.getResource("input/07_1.txt").getFile())));
        puzzleManager.addPuzzle(new Day08_0("08_0", new File(classLoader.getResource("input/08_0.txt").getFile())));
        puzzleManager.addPuzzle(new Day08_1("08_1", new File(classLoader.getResource("input/08_1.txt").getFile())));
        puzzleManager.addPuzzle(new Day09_0("09_0", new File(classLoader.getResource("input/09_0.txt").getFile())));
        puzzleManager.addPuzzle(new Day09_1("09_1", new File(classLoader.getResource("input/09_1.txt").getFile())));

        puzzleManager.runPuzzle("09_1");
    }
}
