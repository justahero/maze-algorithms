package com.justahero.maze.algorithms;

import com.justahero.maze.Board;
import com.justahero.maze.Cell.Direction;
import com.justahero.maze.Rect;

public class RecursiveDivision extends MazeAlgorithm {
    private final Board board;

    public RecursiveDivision(Board board) {
        this.board = board;
    }

    public void generate() {
        resetBoard();
        divide(new Rect(0, 0, board.width(), board.height()));
        fireUpdate();
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

    private void divide(Rect rect) {
        int width = rect.width();
        int height = rect.height();
        if (width < 2 || height < 2) {
            return;
        }

        fireUpdate();

        if (width > height) {
            divideVertical(rect);
        } else if (height > width) {
            divideHorizontal(rect);
        } else {
            boolean horizontal = rand(2) == 0;
            if (horizontal) {
                divideHorizontal(rect);
            } else {
                divideVertical(rect);
            }
        }
    }

    private void divideHorizontal(Rect rect) {
        //int h = rect.h / 2 + rand(rect.h / 2);
        int h = rand(rect.h - 1) + 1;
        int p = rand(rect.w);
        for (int x = rect.left(); x < rect.right(); x++) {
            if (x != rect.x + p) {
                board.cell(x, rect.top() + h).setWall(Direction.North);
            }
        }

        divide(new Rect(rect.x, rect.y,     rect.w, h));
        divide(new Rect(rect.x, rect.y + h, rect.w, rect.h - h));
    }

    private void divideVertical(Rect rect) {
        //int w = rect.w / 2 + rand(rect.w / 2);
        int w = rand(rect.w - 1) + 1;
        int p = rand(rect.h);
        for (int y = rect.top(); y < rect.bottom(); y++) {
            if (y != rect.y + p) {
                board.cell(rect.left() + w, y).setWall(Direction.West);
            }
        }

        divide(new Rect(rect.x,     rect.y, w         , rect.h));
        divide(new Rect(rect.x + w, rect.y, rect.w - w, rect.h));
    }
}

