package com.justahero.maze;

import com.justahero.maze.algorithms.RecursiveDivision;

public class Maze {
    public static void main(String[] args) {
        Board board = new Board(48, 40);
        Window window = new Window(480, 424, board);
        RecursiveDivision algorithm = new RecursiveDivision(board);
        algorithm.addMazeListener(window.getMazePanel());
        algorithm.generate();
    }
}
