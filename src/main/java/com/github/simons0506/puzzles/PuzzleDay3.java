package com.github.simons0506.puzzles;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class PuzzleDay3 extends Puzzle {

    public PuzzleDay3() {
        super("inputDay3.txt");
    }

    @Override
    protected long solvePart1(List<String> input) {
        return input
            .stream()
            .map(this::addUpAllMultiplicationsFor)
            .reduce(0L, Long::sum);
    }

    @Override
    protected long solvePart2(List<String> input) {
        return input
            .stream()
            .reduce(String::concat)
            .map(this::addUpEnabledMultiplicationsFor)
            .orElse(0L);
    }

    private long addUpAllMultiplicationsFor(String line) {
        return Pattern
            .compile("mul\\(\\d{1,3},\\d{1,3}\\)")
            .matcher(line)
            .results()
            .map(MatchResult::group)
            .map(this::multiplyFromMulExpression)
            .reduce(0L, Long::sum);
    }

    private long addUpEnabledMultiplicationsFor(String line) {
        List<String> foundPatterns = Pattern
            .compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)")
            .matcher(line)
            .results()
            .map(MatchResult::group)
            .toList();
        boolean enabled = true;
        long sum = 0L;
        for (String foundPattern : foundPatterns) {
            if (isEnableOperation(foundPattern)) {
                enabled = true;
            } else if (isDisableOperation(foundPattern)) {
                enabled = false;
            } else if (enabled) {
                sum += multiplyFromMulExpression(foundPattern);
            }
        }
        return sum;
    }

    private boolean isEnableOperation(String pattern) {
        return pattern.matches("do\\(\\)");
    }

    private boolean isDisableOperation(String pattern) {
        return pattern.matches("don't\\(\\)");
    }

    private long multiplyFromMulExpression(String mulExpression) {
        return Pattern
            .compile("\\d{1,3}")
            .matcher(mulExpression)
            .results()
            .map(MatchResult::group)
            .map(Long::parseLong)
            .reduce((a, b) -> a * b)
            .orElse(0L);
    }
}
