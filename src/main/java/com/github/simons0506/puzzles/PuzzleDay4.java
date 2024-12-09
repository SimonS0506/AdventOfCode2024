package com.github.simons0506.puzzles;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PuzzleDay4 extends Puzzle {

    public PuzzleDay4() {
        super("inputDay4.txt");
    }

    @Override
    protected long solvePart1(List<String> input) {
        char[][] grid = convertToGrid(input);
        return countHorizontally(grid) + countVertically(grid) + countDiagonally(grid);
    }

    @Override
    protected long solvePart2(List<String> input) {
        return 0;
    }

    private long countHorizontally(char[][] grid) {
        long sum = 0L;
        for (char[] chars : grid) {
            sum += countXmasAppearances(chars);
        }
        return sum;
    }

    private long countVertically(char[][] grid) {
        long sum = 0L;
        for (int column = 0; column < grid[0].length; column++) {
            char[] verticalLine = getVerticalLine(grid, column);
            sum += countXmasAppearances(verticalLine);
        }
        return sum;
    }

    private long countDiagonally(char[][] grid) {
        long sum = 0L;
        for (char[] diagonal : getDiagonals(grid)) {
            sum += countXmasAppearances(diagonal);
        }
        return sum;
    }

    private List<char[]> getDiagonals(char[][] grid) {
        List<char[]> diagonals = new ArrayList<>(getDiagonalsLeftToRight(grid));
        diagonals.addAll(getDiagonalsRightToLeft(grid));
        return diagonals;
    }

    private List<char[]> getDiagonalsLeftToRight(char[][] grid) {
        List<char[]> diagonals = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            List<Character> diagonal = new ArrayList<>();
            for (int col = 0; col < grid[row].length; col++) {
                if(row + col < grid.length) {
                    diagonal.add(grid[row + col][col]);
                }
            }
            diagonals.add(toCharArray(diagonal));
        }
        for (int col = 1; col < grid[0].length; col++) {
            List<Character> diagonal = new ArrayList<>();
            for (int row = 0; row < grid.length; row++) {
                if (row + col < grid[0].length) {
                    diagonal.add(grid[row][row + col]);
                }
            }
            diagonals.add(toCharArray(diagonal));
        }
        return diagonals;
    }

    private List<char[]> getDiagonalsRightToLeft(char[][] grid) {
        List<char[]> diagonals = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            List<Character> diagonal = new ArrayList<>();
            for (int col = grid[row].length - 1; col >= 0; col--) {
                int rowToCheck = row + (grid[row].length - col - 1);
                if(rowToCheck < grid.length) {
                    diagonal.add(grid[rowToCheck][col]);
                }
            }
            diagonals.add(toCharArray(diagonal));
        }
        for (int col = grid[0].length - 2; col >= 0; col--) {
            List<Character> diagonal = new ArrayList<>();
            for (int row = 0; row < grid.length; row++) {
                int colToCheck = col - row;
                if (colToCheck >= 0) {
                    diagonal.add(grid[row][colToCheck]);
                }
            }
            diagonals.add(toCharArray(diagonal));
        }
        return diagonals;
    }

    private char[] getVerticalLine(char[][] grid, int index) {
        char[] verticalLine = new char[grid.length];
        for(int row = 0; row < grid.length; row++) {
            verticalLine[row] = grid[row][index];
        }
        return verticalLine;
    }

    private char[][] convertToGrid(List<String> input) {
        char[][] grid = new char[input.size()][];
        for (int index = 0; index < input.size(); index++) {
            grid[index] = input.get(index).toCharArray();
        }
        return grid;
    }

    private long countXmasAppearances(char[] chars) {
        String concatenated = concatenate(chars);
        long countForwards = Pattern
            .compile("XMAS")
            .matcher(concatenated)
            .results()
            .count();
        long countBackwards = Pattern
            .compile("SAMX")
            .matcher(concatenated)
            .results()
            .count();
        return countForwards + countBackwards;
    }

    private String concatenate(char[] chars) {
        StringBuilder concatenated = new StringBuilder();
        for (char c : chars) {
            concatenated.append(c);
        }
        return concatenated.toString();
    }

    private char[] toCharArray(List<Character> characterList) {
        return characterList.stream().map(String::valueOf).collect(Collectors.joining()).toCharArray();
    }
}
