package nl.jordanvanbeijnhem.puzzle.days;

import nl.jordanvanbeijnhem.Main;
import nl.jordanvanbeijnhem.puzzle.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day04_0 extends Puzzle {

    public Day04_0(String id, File inputData) {
        super(id, inputData);
    }

    @Override
    public void run() {
        List<String> lines = getLines();
        String[] bingoNumberStrings = lines.get(0).split(",");
        List<String> boardStrings = lines.subList(2, lines.size());
        List<BingoBoard> boards = getBingoBoards(boardStrings);

        int result = 0;
        for (String bingoNumberString : bingoNumberStrings) {
            int bingoNumber = Integer.parseInt(bingoNumberString);
            for (BingoBoard board : boards) {
                board.markNumbers(bingoNumber);
                if (board.isComplete()) {
                    result = board.getSumOfUnmarkedNumbers() * bingoNumber;
                    break;
                }
            }
            if (result != 0) break;
        }
        Main.LOGGER.log(Level.INFO, "{0}", result);
    }

    private List<BingoBoard> getBingoBoards(List<String> boardStrings) {
        List<BingoBoard> boards = new ArrayList<>();
        BingoBoard currentBoard = new BingoBoard(5, 5);
        int currentRow = 0;
        for (String line : boardStrings) {
            if (line.isEmpty()) {
                boards.add(currentBoard);
                currentBoard = new BingoBoard(5, 5);
                currentRow = 0;
                continue;
            }
            String[] rowValues = Arrays.stream(line.split(" ")).filter(v -> !v.isEmpty()).toArray(String[]::new);
            for (int currentColumn = 0; currentColumn < rowValues.length; currentColumn++) {
                currentBoard.getBoard().get(currentRow).get(currentColumn).setValue(Integer.parseInt(rowValues[currentColumn]));
            }
            currentRow++;
        }
        boards.add(currentBoard);
        return boards;
    }

    private class BingoBoard {

        private int rows;
        private int columns;
        private List<List<Cell>> board = new ArrayList<>();

        public BingoBoard(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            for (int i = 0; i < this.rows; i++) {
                board.add(IntStream.range(0, this.columns)
                        .mapToObj(y -> new Cell(0, false))
                        .collect(Collectors.toList()));
            }
        }

        public void markNumbers(int numberToMark) {
            for (List<Cell> cells : board) {
                for (Cell cell : cells) {
                    if (cell.value == numberToMark) cell.setMarked(true);
                }
            }
        }

        public boolean isComplete() {
            if (checkRows()) return true;
            return checkColumns();
        }

        public int getSumOfUnmarkedNumbers() {
            int sum = 0;
            for (List<Cell> cells : board) {
                for (Cell cell : cells) {
                    if (!cell.isMarked()) sum += cell.getValue();
                }
            }
            return sum;
        }

        private boolean checkRows() {
            boolean completeRow = false;
            for (int i = 0; i < rows; i++) {
                boolean rowMatched = true;
                for (int j = 0; j < columns; j++) {
                    if (!board.get(i).get(j).isMarked()) {
                        rowMatched = false;
                        break;
                    }
                }
                if (rowMatched) {
                    completeRow = true;
                    break;
                }
            }
            return completeRow;
        }

        private boolean checkColumns() {
            boolean completeColumn = false;
            for (int j = 0; j < columns; j++) {
                boolean columnMatched = true;
                for (int i = 0; i < rows; i++) {
                    if (!board.get(i).get(j).isMarked()) {
                        columnMatched = false;
                        break;
                    }
                }
                if (columnMatched) {
                    completeColumn = true;
                    break;
                }
            }
            return completeColumn;
        }

        public List<List<Cell>> getBoard() {
            return board;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (List<Cell> cells : board) {
                String[] cellStrings = cells.stream().map(cell -> cell.getValue() + (cell.isMarked() ? "*" : " ")).toArray(String[]::new);
                stringBuilder.append(String.join(" ", cellStrings));
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }

        private class Cell {

            private int value;
            private boolean marked;

            public Cell(int value, boolean marked) {
                this.value = value;
                this.marked = marked;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public boolean isMarked() {
                return marked;
            }

            public void setMarked(boolean marked) {
                this.marked = marked;
            }
        }
    }
}
