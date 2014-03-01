package com.justahero.maze.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.justahero.maze.Board;
import com.justahero.maze.Cell.Direction;
import com.justahero.maze.Rect;

public abstract class MazeAlgorithm {
    private static final Random random = new Random();

    protected final Board board;
    private final List<MazeListener> listeners = new ArrayList<MazeListener>();

    public MazeAlgorithm(Board board) {
        this.board = board;
    }

    public void addMazeListener(MazeListener listener) {
        listeners.add(listener);
    }

    public void removeMazeListener(MazeListener listener) {
        listeners.remove(listener);
    }

    public static int rand(int range) {
        return random.nextInt(range);
    }

    public void fireUpdate() {
        for (MazeListener listener : listeners) {
            listener.onUpdate();
        }
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public abstract void generate();

    public void resetBoard() {
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

    protected void initBoard() {
        for (int x = 0; x < board.width(); x++) {
            for (int y = 0; y < board.height(); y++) {
                board.cell(x, y).setWalls();
            }
        }
    }
}
