package com.justahero.maze.algorithms;

import com.justahero.maze.Board;
import com.justahero.maze.Cell;
import com.justahero.maze.Cell.Direction;
import com.justahero.maze.Rect;

public class RecursiveDivision extends MazeAlgorithm {
    private final Board board;

    public RecursiveDivision(Board board) {
        this.board = board;
    }

    public void generate() {
        resetBoard();
        int w = board.width();
        int h = board.height();
        divide(0, 0, w, h, chooseOrientation(w, h));
    }

    private void resetBoard() {
        for (int y = 0; y < board.height(); y++) {
            for (int x = 0; x < board.width(); x++) {
                board.cell(x, y).clearWalls();
            }
        }
        Rect rect = new Rect(0, 0, board.width() - 1, board.height() - 1);
        for (int x = 0; x < board.width(); x++) {
            board.cell(x, rect.top()).setWall(Direction.North);
            board.cell(x, rect.bottom()).setWall(Direction.South);
        }
        for (int y = 0; y < board.height(); y++) {
            board.cell(rect.left(), y).setWall(Direction.West);
            board.cell(rect.right(), y).setWall(Direction.East);
        }
    }

    private void divide(int x, int y, int width, int height, Cell.Direction dir) {
        if (width < 2 || height < 2) {
            return;
        }

        fireUpdate();

        int wx = x + (dir.isHorizontal() ? 0 : rand(width - 2));
        int wy = y + (dir.isHorizontal() ? rand(height - 2) : 0);

        int px = wx + (dir.isHorizontal() ? rand(width) : 0);
        int py = wy + (dir.isHorizontal() ? 0 : rand(height));

        int dx = dir.isHorizontal() ? 1 : 0;
        int dy = dir.isHorizontal() ? 0 : 1;

        int length = dir.isHorizontal() ? width : height;

        for (int i = 0; i < length; i++) {
            if (wx != px || wy != py) {
                board.cell(wx, wy).setWall(dir);
            }
            wx += dx;
            wy += dy;
        }

        int nx = x;
        int ny = y;
        int w = dir.isHorizontal() ? width : wx - x + 1;
        int h = dir.isHorizontal() ? wy - y + 1 : height;
        divide(nx, ny, w, h, chooseOrientation(w, h));

        nx = dir.isHorizontal() ? x : wx + 1;
        ny = dir.isHorizontal() ? wy + 1 : y;
        w = dir.isHorizontal() ? width : x + width - wx - 1;
        h = dir.isHorizontal() ? y + height - wy - 1 : height;
        divide(nx, ny, w, h, chooseOrientation(w, h));
    }

    private Direction chooseOrientation(int width, int height) {
        if (width < height) {
            return Direction.East;
        } else if (height < width) {
            return Direction.North;
        }
        return (rand(2) == 0) ? Direction.East : Direction.North;
    }
}

