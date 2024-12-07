package com.github.simons0506.puzzles;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class PuzzleDay1 extends Puzzle {

    public PuzzleDay1() {
        super("inputDay1.txt");
    }

    @Override
    protected long solvePart1(List<String> input) {
        List<Integer> leftNumbers = getLeftNumbers(input).stream().sorted(Integer::compareTo).toList();
        List<Integer> rightNumbers = getRightNumbers(input).stream().sorted(Integer::compareTo).toList();
        long totalDifference = 0;
        for (int index = 0; index < leftNumbers.size(); index++) {
            totalDifference += Math.abs(leftNumbers.get(index) - rightNumbers.get(index));
        }
        return totalDifference;
    }

    @Override
    protected long solvePart2(List<String> input) {
        Map<Integer, Long> countPerNumberInRightList = getRightNumbers(input)
            .stream()
            .collect(groupingBy(Function.identity(), Collectors.counting()));
        return getLeftNumbers(input)
            .stream()
            .map(number -> number * countPerNumberInRightList.getOrDefault(number, 0L))
            .reduce(Long::sum)
            .orElse(0L);
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
