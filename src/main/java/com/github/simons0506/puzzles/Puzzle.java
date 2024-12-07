package com.github.simons0506.puzzles;

import com.github.simons0506.FileReader;

import java.util.List;

public abstract class Puzzle {

    private final String fileName;

    protected Puzzle(String fileName) {
        this.fileName = fileName;
    }

    public int solve() {
        return solveWithInput(getInput());
    }

    protected abstract int solveWithInput(List<String> input);

    private List<String> getInput() {
        return FileReader.read(fileName);
    }
}
