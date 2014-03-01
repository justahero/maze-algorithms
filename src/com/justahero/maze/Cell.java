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
        public boolean isHorizontal() {
            return dir == 1 || dir == 3;
        }
        public Direction opposite() {
            switch (dir) {
                case 0: return South;
                case 1: return West;
                case 2: return North;
                case 3: return East;
            }
            return null;
        }
        public int dx() {
            switch (dir) {
                case 0: return  0;
                case 1: return  1;
                case 2: return  0;
                case 3: return -1;
            }
            return 0;
        }
        public int dy() {
            switch (dir) {
                case 0: return -1;
                case 1: return  0;
                case 2: return  1;
                case 3: return  0;
            }
            return 0;
        }
    }

    private final boolean walls[] = new boolean[4];
    private final Cell[] neighbors = new Cell[4];

    private final int x;
    private final int y;
    private boolean visited = false;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        clearWalls();
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void setNeighbor(Direction dir, Cell cell) {
        neighbors[dir.value()] = cell;
        cell.neighbors[dir.opposite().value()] = this;
    }

    public Cell neighbor(Direction dir) {
        return neighbors[dir.value()];
    }

    public boolean isVisited() {
        return visited;
    }

    public void visit() {
        visited = true;
    }

    public void unvisit() {
        visited = false;
    }

    public boolean isSolid(Direction dir) {
        return walls[dir.value()];
    }

    public boolean canGoTo(Direction dir) {
        return !walls[dir.value()];
    }

    public void setWall(Direction dir) {
        walls[dir.value()] = true;
    }

    public void removeWall(Direction dir) {
        walls[dir.value()] = false;
    }

    public void clearWalls() {
        setWalls(false);
    }

    public void setWalls() {
        setWalls(true);
    }

    public String toString() {
        return "x: " + x + ", y " + y;
    }

    private void setWalls(boolean enable) {
        for (int i = 0; i < walls.length; i++) {
            walls[i] = enable;
        }
    }
}

