package com.justahero.maze.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeAlgorithm {
    private final List<MazeListener> listeners = new ArrayList<MazeListener>();
    private static final Random random = new Random();

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
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
