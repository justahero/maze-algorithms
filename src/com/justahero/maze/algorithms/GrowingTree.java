package com.justahero.maze.algorithms;

import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.justahero.maze.Board;
import com.justahero.maze.Cell;
import com.justahero.maze.Cell.Direction;

public class GrowingTree extends MazeAlgorithm {
    private List<Direction> directions = asList(Direction.North, Direction.East, Direction.South, Direction.West);

    public GrowingTree(Board board) {
        super(board);
    }

    @Override
    public void generate() {
        resetBoard();
        initBoard();
        find();
        fireUpdate();
    }

    private int nextIndex(List<Cell> cells) {
        return rand(cells.size());
    }

    private void find() {
        int x = rand(board.width());
        int y = rand(board.height());
        List<Cell> cells = new LinkedList<Cell>();
        cells.add(board.cell(x, y));

        while (!cells.isEmpty()) {
            int index = nextIndex(cells);
            Cell cell = cells.get(index);
            cell.visit();

            Collections.shuffle(directions);
            for (Direction dir : directions) {
                Cell neighbor = cell.neighbor(dir);
                if (neighbor != null && !neighbor.isVisited() && cell.isSolid(dir)) {
                    cell.removeWall(dir);
                    neighbor.removeWall(dir.opposite());
                    neighbor.visit();

                    cells.add(neighbor);
                    index = -1;
                    fireUpdate();
                    break;
                }
            }

            if (index != -1) {
                cells.remove(index);
            }
        }
    }
}
