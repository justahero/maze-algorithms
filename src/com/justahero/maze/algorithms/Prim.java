package com.justahero.maze.algorithms;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.justahero.maze.Board;
import com.justahero.maze.Cell;
import com.justahero.maze.Cell.Direction;

public class Prim extends MazeAlgorithm {
    private static List<Direction> Directions = asList(Direction.North, Direction.East, Direction.South, Direction.West);
    public Prim(Board board) {
        super(board);
    }

    @Override
    public void generate() {
        resetBoard();
        initBoard();

        find();

        fireUpdate();
    }

    private void find() {
        List<Cell> frontier = new LinkedList<Cell>();
        mark(rand(board.width()), rand(board.height()), frontier);
        while (!frontier.isEmpty()){
            int index = rand(frontier.size());
            Cell cell = frontier.remove(index);
            List<Cell> neighbors = neighbors(cell, frontier);

            index = rand(neighbors.size());
            Cell neighbor = neighbors.get(index);

            Direction dir = direction(cell, neighbor);
            cell.removeWall(dir);
            neighbor.removeWall(dir.opposite());

            mark(cell.x(), cell.y(), frontier);
            fireUpdate();
        }
    }

    private Direction direction(Cell next, Cell neighbor) {
        for (Direction dir : Directions) {
            if (next.neighbor(dir) == neighbor) {
                return dir;
            }
        }
        return null;
    }

    private List<Cell> neighbors(Cell cell, List<Cell> frontier) {
        List<Cell> neighbors = new ArrayList<Cell>();
        for (Direction dir : Directions) {
            Cell neighbor = cell.neighbor(dir);
            if (neighbor != null && neighbor.isVisited() && !frontier.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    private void mark(int x, int y, List<Cell> frontier) {
        Cell cell = board.cell(x, y);
        cell.visit();
        for (Direction dir : Directions) {
            Cell neighbor = cell.neighbor(dir);
            if (neighbor != null && !neighbor.isVisited() && !frontier.contains(neighbor)) {
                frontier.add(neighbor);
            }
        }
    }
}

