package com.justahero.maze;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.justahero.maze.algorithms.MazeListener;

@SuppressWarnings("serial")
public class OutputPanel extends JPanel implements MazeListener {
    private Board board = null;

    public OutputPanel(Board board) {
        this.board = board;
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        int cellHeight = getHeight() / board.height();
        int cellWidth = getWidth() / board.width();

        Rect rect = new Rect();
        for (int cellx = 0; cellx < board.width(); cellx++) {
            for (int celly = 0; celly < board.height(); celly++) {
                Cell cell = board.cell(cellx, celly);

                rect.setPos(cellx * cellWidth, celly * cellHeight);
                rect.setSize(cellWidth, cellHeight);

                if (cell.isSolid(Cell.Direction.North)) {
                    g.drawLine(rect.left(), rect.top(), rect.right(), rect.top());
                }
                if (cell.isSolid(Cell.Direction.East)) {
                    g.drawLine(rect.right(), rect.top(), rect.right(), rect.bottom());
                }
                if (cell.isSolid(Cell.Direction.South)) {
                    g.drawLine(rect.left(), rect.bottom(), rect.right(), rect.bottom());
                }
                if (cell.isSolid(Cell.Direction.West)) {
                    g.drawLine(rect.left(), rect.top(), rect.left(), rect.bottom());
                }
            }
        }
    }

    @Override
    public void onUpdate() {
        repaint();
    }
}
