package nl.jordanvanbeijnhem;

import nl.jordanvanbeijnhem.puzzle.PuzzleManager;
import nl.jordanvanbeijnhem.puzzle.days.Day01_0;
import nl.jordanvanbeijnhem.puzzle.days.Day01_1;
import nl.jordanvanbeijnhem.puzzle.days.Day02_0;
import nl.jordanvanbeijnhem.puzzle.days.Day02_1;

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

        puzzleManager.runPuzzle("02_1");
    }
}
