package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Pattern;

public class Day08_1 extends Puzzle {

    public Day08_1(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        List<String> lines = getLines();
        int result = 0;
        for (String line : lines) {
            String[] lineSplit = line.split(Pattern.quote("|"));
            String[] signalPatterns = lineSplit[0].split(" ");
            Map<String, Integer> patternMap = new HashMap<>();
            String eightValue = "";
            for (String signal : signalPatterns) {
                if (signal.length() == 2) patternMap.put(signal, 1);
                if (signal.length() == 4) patternMap.put(signal, 4);
                if (signal.length() == 3) patternMap.put(signal, 7);
                if (signal.length() == 7) {
                    eightValue = signal;
                    patternMap.put(signal, 8);
                }
            }


            List<Map<Character, Integer>> possibleSegmentValues = new ArrayList<>(7);
            List<Character> actualSegmentValues = new ArrayList<>(7);
            for (int i = 0; i < 7; i++) {
                possibleSegmentValues.add(new HashMap<>());
                actualSegmentValues.add(null);
            }
            for (String pattern : patternMap.keySet()) {
                int[] possibleSegments = new int[]{};
                if (patternMap.get(pattern) == 1) possibleSegments = new int[]{2, 5};
                if (patternMap.get(pattern) == 4) possibleSegments = new int[]{1, 2, 3, 5};
                if (patternMap.get(pattern) == 7) possibleSegments = new int[]{0, 2, 5};
                if (patternMap.get(pattern) == 8) possibleSegments = new int[]{0, 1, 2, 3, 4, 5, 6};
                for (int i = 0; i < pattern.length(); i++) {
                    char c = pattern.charAt(i);
                    for (int possibleSegment : possibleSegments) {
                        if (!possibleSegmentValues.get(possibleSegment).containsKey(c))
                            possibleSegmentValues.get(possibleSegment).put(c, 0);
                        int occurrences = possibleSegmentValues.get(possibleSegment).get(c);
                        possibleSegmentValues.get(possibleSegment).put(c, occurrences + 1);
                    }
                }
            }

            for (Map<Character, Integer> possibleSegmentValue : possibleSegmentValues) {
                int i = 0;
                for (Character key : possibleSegmentValue.keySet()) {
                    if (possibleSegmentValue.get(key) == 1) actualSegmentValues.set(i, key);
                    i++;
                }
            }

            for (int i = 0; i < actualSegmentValues.size(); i++) {
                if (actualSegmentValues.get(i) == null) actualSegmentValues.set(i, eightValue.charAt(i));
            }

            patternMap = new HashMap<>();
            patternMap.put(actualSegmentValues.get(2).toString() + actualSegmentValues.get(5), 1);
            patternMap.put(actualSegmentValues.get(0).toString() + actualSegmentValues.get(2) + actualSegmentValues.get(3) + actualSegmentValues.get(4) + actualSegmentValues.get(6), 2);
            patternMap.put(actualSegmentValues.get(0).toString() + actualSegmentValues.get(2) + actualSegmentValues.get(3) + actualSegmentValues.get(5) + actualSegmentValues.get(6), 3);
            patternMap.put(actualSegmentValues.get(1).toString() + actualSegmentValues.get(2) + actualSegmentValues.get(3) + actualSegmentValues.get(5), 4);
            patternMap.put(actualSegmentValues.get(0).toString() + actualSegmentValues.get(1) + actualSegmentValues.get(3) + actualSegmentValues.get(5) + actualSegmentValues.get(6), 5);
            patternMap.put(actualSegmentValues.get(0).toString() + actualSegmentValues.get(1) + actualSegmentValues.get(3) + actualSegmentValues.get(4) + actualSegmentValues.get(5) + actualSegmentValues.get(6), 6);
            patternMap.put(actualSegmentValues.get(1).toString() + actualSegmentValues.get(2) + actualSegmentValues.get(5), 7);
            patternMap.put(actualSegmentValues.get(0).toString() + actualSegmentValues.get(1) + actualSegmentValues.get(2) + actualSegmentValues.get(3) + actualSegmentValues.get(4) + actualSegmentValues.get(5) + actualSegmentValues.get(6), 8);
            patternMap.put(actualSegmentValues.get(0).toString() + actualSegmentValues.get(1) + actualSegmentValues.get(2) + actualSegmentValues.get(3) + actualSegmentValues.get(5) + actualSegmentValues.get(6), 9);


            String[] outputDigits = lineSplit[1].split(" ");
            StringBuilder outputValue = new StringBuilder();
            for (String outputDigit : outputDigits) {
                for (String key : patternMap.keySet()) {
                    if (key.length() == outputDigit.length()) {
                        boolean match = true;
                        for (int i = 0; i < key.length(); i++) {
                           if (!outputDigit.contains("" + key.charAt(i))) {
                               System.out.println(outputDigit);
                               System.out.println("" + key.charAt(i));
                               match = false;
                               break;
                           }
                        }
                        if (match) {
                            System.out.println("test");
                            outputValue.append(patternMap.get(outputDigit).toString());
                        }
                    }
                }
            }
            System.out.println(outputValue);
            result += Integer.parseInt(outputValue.toString());
        }
        Main.LOGGER.log(Level.INFO, "{0}", result);
    }
}
