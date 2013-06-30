package com.justahero.maze.algorithms;

import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.List;

import com.justahero.maze.Board;
import com.justahero.maze.Cell.Direction;

public class RecursiveBacktracker extends MazeAlgorithm {
    public RecursiveBacktracker(Board board) {
        super(board);
    }

    @Override
    public void generate() {
        resetBoard();
        initBoard();
        int rx = rand(board.width());
        int ry = rand(board.height());
        findPassage(rx, ry);
        fireUpdate();
    }

    private void findPassage(int x, int y) {
        board.cell(x, y).visit();
        List<Direction> directions = asList(Direction.North, Direction.East, Direction.South, Direction.West);
        Collections.shuffle(directions);

        for (Direction dir : directions) {
            int nx = x + dir.dx();
            int ny = y + dir.dy();

            if (nx >= 0 && nx < board.width() && ny >= 0 && ny < board.height()) {
                if (!board.cell(nx, ny).isVisited()) {
                    board.cell(x, y).removeWall(dir);
                    board.cell(nx, ny).removeWall(dir.opposite());
                    fireUpdate();
                    findPassage(nx, ny);
                }
            }
        }
    }
}
