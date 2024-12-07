package com.github.simons0506.puzzles;

import java.util.List;

public class PuzzleDay1 extends Puzzle {

    public PuzzleDay1() {
        super("inputDay1.txt");
    }

    @Override
    protected int solveWithInput(List<String> input) {
        List<Integer> leftNumbers = getLeftNumbers(input).stream().sorted(Integer::compareTo).toList();
        List<Integer> rightNumbers = getRightNumbers(input).stream().sorted(Integer::compareTo).toList();
        int totalDifference = 0;
        for (int index = 0; index < leftNumbers.size(); index++) {
            totalDifference += Math.abs(leftNumbers.get(index) - rightNumbers.get(index));
        }
        return totalDifference;
    }

    private List<Integer> getLeftNumbers(List<String> input) {
        return input
            .stream()
            .map(line -> line.substring(0, line.indexOf(' ')))
            .map(Integer::parseInt)
            .toList();
    }

    private List<Integer> getRightNumbers(List<String> input) {
        return input
            .stream()
            .map(line -> line.substring(line.lastIndexOf(' ') + 1))
            .map(Integer::parseInt)
            .toList();
    }
}
