package com.github.simons0506;

public record Solution(long solutionPart1, long solutionPart2) {

    @Override
    public String toString() {
        return "Solution to part 1: " + solutionPart1 + "\n" +
            "Solution to part 2: " + solutionPart2;
    }
}
