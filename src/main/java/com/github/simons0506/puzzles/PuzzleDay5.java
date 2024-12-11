package com.github.simons0506.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PuzzleDay5 extends Puzzle {

    public PuzzleDay5() {
        super("inputDay5.txt");
    }

    @Override
    protected long solvePart1(List<String> input) {
        Set<Rule> rules = getRules(input);
        List<int[]> pageNumbersOfUpdates = getPageNumbersOfUpdate(input);
        long sum = 0L;
        for (int[] pageNumbersOfUpdate : pageNumbersOfUpdates) {
            if (isInRightOrder(rules, pageNumbersOfUpdate)) {
                sum += pageNumbersOfUpdate[pageNumbersOfUpdate.length / 2];
            }
        }
        return sum;
    }

    @Override
    protected long solvePart2(List<String> input) {
        Set<Rule> rules = getRules(input);
        List<int[]> pageNumbersOfUpdates = getPageNumbersOfUpdate(input);
        long sum = 0L;
        for (int[] pageNumbersOfUpdate : pageNumbersOfUpdates) {
            if (!isInRightOrder(rules, pageNumbersOfUpdate)) {
                int[] pageNumbersInRightOrder = orderCorrectly(rules, pageNumbersOfUpdate);
                sum += pageNumbersInRightOrder[pageNumbersInRightOrder.length / 2];
            }
        }
        return sum;
    }

    record Rule(int before, int after) {
    }

    private int[] orderCorrectly(Set<Rule> rules, int[] pageNumbersOfUpdate) {
        List<Integer> ordered = new ArrayList<>();
        ordered.add(pageNumbersOfUpdate[0]);
        outerloop:
        for (int index = 1; index < pageNumbersOfUpdate.length; index++) {
            int currentInteger = pageNumbersOfUpdate[index];
            for (int orderedIntegerIndex = 0; orderedIntegerIndex < ordered.size(); orderedIntegerIndex++) {
                if (rules.contains(new Rule(currentInteger, ordered.get(orderedIntegerIndex)))) {
                    ordered.add(orderedIntegerIndex, currentInteger);
                    continue outerloop;
                }
            }
            ordered.add(currentInteger);
        }
        return ordered.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean isInRightOrder(Set<Rule> rules, int[] pageNumbersOfUpdate) {
        for (int i = 0; i < pageNumbersOfUpdate.length; i++) {
            for (int j = i + 1; j < pageNumbersOfUpdate.length; j++) {
                if (rules.contains(new Rule(pageNumbersOfUpdate[j], pageNumbersOfUpdate[i]))) {
                    return false;
                }
            }
        }
        return true;
    }

    private Set<Rule> getRules(List<String> input) {
        return input
            .stream()
            .filter(line -> line.contains("|"))
            .map(line -> new Rule(Integer.parseInt(line.substring(0, 2)), Integer.parseInt(line.substring(3))))
            .collect(Collectors.toSet());
    }

    private List<int[]> getPageNumbersOfUpdate(List<String> input) {
        return input
            .stream()
            .filter(line -> line.contains(","))
            .map(line -> Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray())
            .toList();
    }
}
