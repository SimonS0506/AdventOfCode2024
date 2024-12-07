package com.github.simons0506;

public record Solution(int solutionPart1, int solutionPart2) {

    @Override
    public String toString() {
        return "Solution to part 1: " + solutionPart1 + "\n" +
            "Solution to part 2: " + solutionPart2;
    }
}
