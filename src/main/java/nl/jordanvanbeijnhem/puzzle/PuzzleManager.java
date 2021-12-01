package nl.jordanvanbeijnhem.puzzle;

import java.util.ArrayList;
import java.util.List;

public class PuzzleManager {

    private List<Puzzle> puzzles = new ArrayList<>();

    public void addPuzzle(Puzzle puzzle) {
        this.puzzles.add(puzzle);
    }

    public void runPuzzle(String id) {
        this.puzzles.stream().filter(puzzle -> puzzle.getId().equals(id)).forEach(Puzzle::run);
    }
}
