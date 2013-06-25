package com.justahero.maze;

import com.justahero.maze.algorithms.RecursiveDivision;

public class Maze {
    public static void main(String[] args) {
        Board board = new Board(20, 20);
        Window window = new Window(400, 424, board);
        RecursiveDivision algorithm = new RecursiveDivision(board);
        algorithm.addMazeListener(window.getMazePanel());
        algorithm.generate();
    }
}
