package com.justahero.maze;

import com.justahero.maze.Cell.Direction;

public class Board {
    protected final int width;
    protected final int height;

    protected final Cell[][] cells;

    public Board(int width, int height) {
        this.width  = width;
        this.height = height;
        this.cells  = createCells(width, height);
        linkCells();
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public Cell cell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return cells[x][y];
        }
        return null;
    }

    private static Cell[][] createCells(int width, int height) {
        Cell[][] cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
        return cells;
    }

    private void linkCells() {
        for (int x = 0; x < width - 1; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y].setNeighbor(Direction.East, cells[x + 1][y]);
            }
        }
        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width; x++) {
                cells[x][y].setNeighbor(Direction.South, cells[x][y + 1]);
            }
        }
    }
}
