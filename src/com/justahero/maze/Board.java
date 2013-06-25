package com.justahero.maze;

public class Board {
    private final int width;
    private final int height;

    private final Cell[][] cells;

    public Board(int width, int height) {
        this.width  = width;
        this.height = height;
        this.cells  = createCells(width, height);
    }

    private static Cell[][] createCells(int width, int height) {
        Cell[][] cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < cells.length; y++) {
                cells[x][y] = new Cell();
            }
        }
        return cells;
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public Cell cell(int x, int y) {
        return cells[x][y];
    }

    public void reset() {
    }
}
