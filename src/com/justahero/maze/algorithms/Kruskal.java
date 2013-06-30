package com.justahero.maze.algorithms;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.justahero.maze.Board;
import com.justahero.maze.Cell;
import com.justahero.maze.Cell.Direction;

public class Kruskal extends MazeAlgorithm {
    class Node {
        private Node parent = null;
        public Node() {
        }
        public Node root() {
            return (parent != null) ? parent.root() : this;
        }
        public boolean isconnected(Node node) {
            return root() == node.root();
        }
        public void connect(Node node) {
            node.root().parent = this;
        }
    }

    class Edge {
        public final Cell cell;
        public final Direction dir;
        public Edge(Cell cell, Direction dir) {
            this.cell = cell;
            this.dir = dir;
        }
    }

    private final Node[][] sets;
    
    public Kruskal(Board board) {
        super(board);
        sets = createSets();
    }

    private Node[][] createSets() {
        Node[][] sets = new Node[board.width()][board.height()];
        for (int x = 0; x < board.width(); x++) {
            for (int y = 0; y < board.height(); y++) {
                sets[x][y] = new Node();
            }
        }
        return sets;
    }

    @Override
    public void generate() {
        resetBoard();
        initBoard();
        find();
        fireUpdate();
    }

    private void find() {
        List<Edge> edges = buildEdges();
        Collections.shuffle(edges);

        while (!edges.isEmpty()) {
            Edge edge = edges.remove(0);

            Direction dir = edge.dir;
            Cell cell = edge.cell;
            Cell neighbor = cell.neighbor(edge.dir);

            Node set1 = sets[cell.x()][cell.y()];
            Node set2 = sets[neighbor.x()][neighbor.y()];

            if (!set1.isconnected(set2)) {
                set1.connect(set2);
                cell.removeWall(dir);
                neighbor.removeWall(dir.opposite());

                cell.visit();
                neighbor.visit();
                fireUpdate();
            }
        }
    }

    private List<Edge> buildEdges() {
        List<Edge> edges = new LinkedList<Kruskal.Edge>();
        for (int x = 1; x < board.width(); x++) {
            for (int y = 1; y < board.height(); y++) {
                edges.add(new Edge(board.cell(x, y), Direction.West));
                edges.add(new Edge(board.cell(x, y), Direction.North));
            }
        }
        return edges;
    }
}

