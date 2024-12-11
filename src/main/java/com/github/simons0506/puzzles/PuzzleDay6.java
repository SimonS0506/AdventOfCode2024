package com.github.simons0506.puzzles;

import java.util.List;

public class PuzzleDay6 extends Puzzle {

    public PuzzleDay6() {
        super("inputDay6.txt");
    }

    @Override
    protected long solvePart1(List<String> input) {
        Field[][] map = getMap(input);
        Position position = getPosition(input);
        return countDistinctPositions(map, position);
    }

    @Override
    protected long solvePart2(List<String> input) {
        return 0;
    }

    sealed interface Field {

        record Obstruction() implements Field {
        }

        record Empty() implements Field {
        }

        record Visited() implements Field {
        }
    }

    record Position(int row, int col) {
    }

    enum Direction {

        UP,
        DOWN,
        LEFT,
        RIGHT,
        ;
    }

    private Field[][] getMap(List<String> mapRows) {
        Field[][] map = new Field[mapRows.size()][];
        for (int row = 0; row < mapRows.size(); row++) {
            String rowString = mapRows.get(row);
            char[] chars = rowString.toCharArray();
            map[row] = new Field[rowString.length()];
            for (int col = 0; col < rowString.length(); col++) {
                if (chars[col] == '#') {
                    map[row][col] = new Field.Obstruction();
                } else {
                    map[row][col] = new Field.Empty();
                }
            }
        }
        return map;
    }

    private Position getPosition(List<String> mapRows) {
        for (int row = 0; row < mapRows.size(); row++) {
            String rowString = mapRows.get(row);
            char[] chars = rowString.toCharArray();
            for (int col = 0; col < rowString.length(); col++) {
                if (chars[col] == '^') {
                    return new Position(row, col);
                }
            }
        }
        throw new RuntimeException("Position konnte nicht gefunden werden.");
    }

    private long countDistinctPositions(Field[][] map, Position startingPosition) {
        Direction currentDirection = Direction.UP;
        Position currentPosition = startingPosition;
        while (!isOutOfMap(map, currentPosition)) {
            setAsVisited(map, currentPosition);
            if (nextPositionIsObstructed(map, currentPosition, currentDirection)) {
                currentDirection = nextDirection(currentDirection);
            } else {
                currentPosition = nextPosition(currentPosition, currentDirection);
            }
        }
        return countVisits(map);
    }

    private long countVisits(Field[][] map) {
        long count = 0L;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (map[row][col].equals(new Field.Visited())) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isOutOfMap(Field[][] map, Position position) {
        int width = map[0].length;
        int height = map.length;
        return position.row < 0 || position.row >= height || position.col < 0 || position.col >= width;
    }

    private void setAsVisited(Field[][] map, Position position) {
        map[position.row][position.col] = new Field.Visited();
    }

    private boolean nextPositionIsObstructed(Field[][] map, Position currentPosition, Direction currentDirection) {
        Position nextPosition = nextPosition(currentPosition, currentDirection);
        if (isOutOfMap(map, nextPosition)) {
            return false;
        } else {
            return map[nextPosition.row][nextPosition.col].equals(new Field.Obstruction());
        }
    }

    private Position nextPosition(Position currentPosition, Direction currentDirection) {
        return switch (currentDirection) {
            case UP -> new Position(currentPosition.row - 1, currentPosition.col);
            case DOWN -> new Position(currentPosition.row + 1, currentPosition.col);
            case LEFT -> new Position(currentPosition.row, currentPosition.col - 1);
            case RIGHT -> new Position(currentPosition.row, currentPosition.col + 1);
        };
    }

    private Direction nextDirection(Direction currentDirection) {
        return switch (currentDirection) {
            case UP -> Direction.RIGHT;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
            case RIGHT -> Direction.DOWN;
        };
    }
}
