package com.justahero.maze.algorithms;

import java.util.ArrayList;
import java.util.List;

public class MazeAlgorithm {
    private final List<MazeListener> listeners = new ArrayList<MazeListener>();

    public void addMazeListener(MazeListener listener) {
        listeners.add(listener);
    }

    public void removeMazeListener(MazeListener listener) {
        listeners.remove(listener);
    }

    public static int rand(int range) {
        return (int)(Math.random() * range);
    }

    public void fireUpdate() {
        for (MazeListener listener : listeners) {
            listener.onUpdate();
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
