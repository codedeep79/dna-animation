package com.nth.dna;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * Created by HAU TRUNG NGUYEN <haunt.hcm2015@gmail.com> on Nov 18, 2021
 */
public class Point {

    private final int originX;
    private int x;
    private int y;
    private double angle = 0.0;
    private int z = 0;

    private static final double STEP = 0.1;

    Point(int x, int y, int step) {
        this.originX = x;
        this.x = x;
        this.y = y;

        for (int i = 0; i < step; i++) {
            increment();
        }
    }

    void increment() {
        angle += STEP;
        x = (int) (originX + Math.sin(angle) * 40.0);
        z = (int) (10 + Math.cos(angle) * 10) + 10;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void draw(Graphics graphics, Color foreColor) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(foreColor);
        g.fillOval(x - (z / 4 + 5) / 2, y - (z / 4 + 5) / 2, z / 4 + 5, z / 4 + 5);

    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
