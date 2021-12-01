package nl.jordanvanbeijnhem;

import nl.jordanvanbeijnhem.puzzle.PuzzleManager;
import nl.jordanvanbeijnhem.puzzle.days.Day01_0;

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

        puzzleManager.runPuzzle("01_0");
    }
}