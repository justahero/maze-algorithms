package com.justahero.maze.algorithms;

import com.justahero.maze.Board;

public class RecursiveDivision {
    private final Board board;

    public RecursiveDivision(Board board) {
        this.board = board;
    }

    public void generate() {
        board.reset();
    }
}
