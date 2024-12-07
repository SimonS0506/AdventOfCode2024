package com.github.simons0506.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzleDay2 extends Puzzle {

    public PuzzleDay2() {
        super("inputDay2.txt");
    }

    @Override
    protected long solvePart1(List<String> input) {
        return input
            .stream()
            .map(this::getNumbersFromLine)
            .filter(numbers -> isStrictlyMonotonicallyIncreasingByAtMost3(numbers) || isStrictlyMonotonicallyDecreasingByAtMost3(numbers))
            .count();
    }

    @Override
    protected long solvePart2(List<String> input) {
        return input
            .stream()
            .map(this::getNumbersFromLine)
            .filter(this::isSafeWithProblemDampener)
            .count();
    }

    private boolean isSafeWithProblemDampener(List<Integer> numbers) {
        boolean isSafe = false;
        for(int index = 0; index < numbers.size(); index++) {
            List<Integer> listWithoutNumberAtIndex = new ArrayList<>(numbers);
            listWithoutNumberAtIndex.remove(index);
            if (isStrictlyMonotonicallyIncreasingByAtMost3(listWithoutNumberAtIndex) || isStrictlyMonotonicallyDecreasingByAtMost3(listWithoutNumberAtIndex)) {
                isSafe = true;
            }
        }
        return isSafe;
    }

    private boolean isStrictlyMonotonicallyIncreasingByAtMost3(List<Integer> numbers) {
        for (int index = 0; index < numbers.size() - 1; index++) {
            Integer numberAtIndex = numbers.get(index);
            Integer numberAtIndexPlus1 = numbers.get(index + 1);
            if (numberAtIndexPlus1 - numberAtIndex > 3 || numberAtIndexPlus1 - numberAtIndex < 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isStrictlyMonotonicallyDecreasingByAtMost3(List<Integer> numbers) {
        for (int index = 0; index < numbers.size() - 1; index++) {
            Integer numberAtIndex = numbers.get(index);
            Integer numberAtIndexPlus1 = numbers.get(index + 1);
            if (numberAtIndex - numberAtIndexPlus1 > 3 || numberAtIndex - numberAtIndexPlus1 < 1) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> getNumbersFromLine(String line) {
        return Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
    }
}
