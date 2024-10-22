package com.justahero.maze;

import com.justahero.maze.algorithms.*;

public class SampleMaze {
    public static void main(String[] args) {
        Board board = new Board(24, 20);
        Window window = new Window(480, 424, board);
//        MazeAlgorithm algorithm = new RecursiveDivision(board);
        MazeAlgorithm algorithm = new RecursiveBacktracker(board);
//        MazeAlgorithm algorithm = new Prim(board);
//        MazeAlgorithm algorithm = new Kruskal(board);
//        MazeAlgorithm algorithm = new GrowingTree(board);
        algorithm.addMazeListener(window.getMazePanel());
        algorithm.generate();
    }
}
