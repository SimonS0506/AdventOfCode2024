package com.github.simons0506.puzzles;

import com.github.simons0506.FileReader;
import com.github.simons0506.Solution;

import java.util.List;

public abstract class Puzzle {

    private final String fileName;

    protected Puzzle(String fileName) {
        this.fileName = fileName;
    }

    public Solution solve() {
        List<String> input = getInput();
        return new Solution(solvePart1(input), solvePart2(input));
    }

    protected abstract int solvePart1(List<String> input);

    protected abstract int solvePart2(List<String> input);

    private List<String> getInput() {
        return FileReader.read(fileName);
    }
}
