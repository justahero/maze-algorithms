package com.justahero.maze;

public class Board {
    private final int width;
    private final int height;

    private final Cell[][] cells;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];
    }

    public void reset() {
        
    }
}
