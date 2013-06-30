package com.justahero.maze;

import javax.swing.JFrame;

import com.justahero.maze.algorithms.MazeListener;

@SuppressWarnings("serial")
public class Window extends JFrame {
    private final OutputPanel mazePanel;

    public Window(int width, int height, Board board) {
        super("Maze");
        mazePanel = new OutputPanel(board);
        initUI(width, height);
    }

    private void initUI(int width, int height) {
        setDefaultLookAndFeelDecorated(true);
        setSize(width, height);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mazePanel);
        setVisible(true);
    }

    public MazeListener getMazePanel() {
        return mazePanel;
    }
}
