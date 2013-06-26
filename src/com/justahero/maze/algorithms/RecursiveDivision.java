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
            boolean vertical = (rand(2) == 1);
            if (vertical) {
                divideVertical(rect);
            } else {
                divideHorizontal(rect);
            }
        }

        fireUpdate();
    }

    private void divideHorizontal(Rect rect) {
        int divide = rect.top() + 1 + rand(rect.height() - 2);
        for (int x = rect.left(); x < rect.right() + 1; x++) {
            board.cell(x, divide).setWall(Direction.North);
        }

        int clearSpace = rect.left() + rand(rect.width() - 1);
        board.cell(clearSpace, divide).removeWall(Direction.North);

        Rect top = new Rect(rect.left(), rect.top(), rect.width(), divide);
        Rect bottom = new Rect(rect.left(), divide, rect.width(), rect.height() - divide);
        divide(top);
        divide(bottom);
    }

    private void divideVertical(Rect rect) {
        int divide = rect.left() + 1 + rand(rect.width() - 2);
        for (int y = rect.top(); y < rect.bottom(); y++) {
            board.cell(divide, y).setWall(Direction.East);
        }

        int clearSpace = rect.top() + rand(rect.height() - 1);
        board.cell(divide, clearSpace).removeWall(Direction.East);

        Rect left  = new Rect(rect.left(), rect.top(), divide, rect.height());
        Rect right = new Rect(divide, rect.top(), rect.width() - divide, rect.height());
        divide(left);
        divide(right);
    }
}

