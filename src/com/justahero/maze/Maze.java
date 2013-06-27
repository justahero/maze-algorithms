package com.justahero.maze;

import com.justahero.maze.algorithms.MazeAlgorithm;
import com.justahero.maze.algorithms.RecursiveBacktracker;

public class Maze {
    public static void main(String[] args) {
        Board board = new Board(24, 20);
        Window window = new Window(480, 424, board);
        //MazeAlgorithm algorithm = new RecursiveDivision(board);
        MazeAlgorithm algorithm = new RecursiveBacktracker(board);
        algorithm.addMazeListener(window.getMazePanel());
        algorithm.generate();
    }
}
