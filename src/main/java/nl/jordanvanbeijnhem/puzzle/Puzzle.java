package nl.jordanvanbeijnhem.puzzle;

import nl.jordanvanbeijnhem.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public abstract class Puzzle {

    private String id;
    private File inputData;

    protected Puzzle(String id, File inputData) {
        this.id = id;
        this.inputData = inputData;
    }

    public abstract void run();

    public String getId() {
        return id;
    }

    public File getInputData() {
        return inputData;
    }

    public List<String> getLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputData))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException exception) {
            Main.LOGGER.log(Level.SEVERE, "Error occurred while reading input file!", exception);
            exception.printStackTrace();
        }
        return lines;
    }
}
