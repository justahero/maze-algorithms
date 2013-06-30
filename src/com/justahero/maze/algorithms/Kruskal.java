package com.justahero.maze.algorithms;

import com.justahero.maze.Board;

public class Kruskal extends MazeAlgorithm {
    public Kruskal(Board board) {
        super(board);
    }

    @Override
    public void generate() {
        resetBoard();
        initBoard();
        fireUpdate();
    }
}
