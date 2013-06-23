package com.justahero.maze;

public class Cell {
    public enum Direction {
        North(0), East(1), South(2), West(3);
        private int dir;
        private Direction(int dir) {
            this.dir = dir;
        }
        public int value() {
            return dir;
        }
    }

    private final boolean walls[] = new boolean[4];

    public Cell() {
        for (int i = 0; i < walls.length; i++) {
            walls[i] = false;
        }
    }

    public void setWall(Direction dir) {
        walls[dir.value()] = true;
    }

    public void removeWall(Direction dir) {
        walls[dir.value()] = false;
    }
}
