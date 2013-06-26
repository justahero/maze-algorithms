package com.justahero.maze;

public class Rect {
    public int x = 0;
    public int y = 0;
    public int w = 0;
    public int h = 0;

    public Rect(int x, int y, int width, int height) {
        set(x, y, width, height);
    }

    public Rect() {
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {
        this.w = width;
        this.h = height;
    }

    public int left() {
        return x;
    }

    public int top() {
        return y;
    }

    public int right() {
        return x + w;
    }

    public int width() {
        return w;
    }

    public int height() {
        return h;
    }

    public int bottom() {
        return y + h;
    }

    public void set(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
    }
}
