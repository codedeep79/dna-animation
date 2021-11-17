package com.nth.dna;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * Created by HAU TRUNG NGUYEN <haunt.hcm2015@gmail.com> on Nov 18, 2021
 */
public class Animation extends JPanel implements Runnable {

    private static final Color BACK_COLOR = new Color(0x0, 0x99, 0x00);
    private static final Color FORE_COLOR = new Color(255, 255, 255);
    private ArrayList<Point> points;

    Animation(ArrayList<Point> points) {
        this.points = points;
    }

    @Override
    public void run() {

        long start = System.nanoTime();
        long now;
        double timeElapsed = 0.0;
        double FPS = 40.0;

        while (true) {

            now = System.nanoTime();
            timeElapsed += ((now - start) / 1_000_000_000.0) * FPS;
            start = System.nanoTime();

            if (timeElapsed > 1) {
                update();
                repaint();
                timeElapsed--;
            }
            sleepThread();
        }
    }

    private void update() {
        points.forEach((point) -> point.increment());
    }

    private void sleepThread() {
        try {
            sleep(10);
        } catch (InterruptedException ex) {
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (System.getProperty("os.name").equals("Linux")) {
            Toolkit.getDefaultToolkit().sync();
        }

        setBackground(BACK_COLOR);
        drawDNA(graphics);
    }

    private void drawDNA(Graphics graphics) {
        drawBridges(graphics);
        drawRidges(graphics);
        drawNodes(graphics);
    }

    private void drawRidges(Graphics graphics) {

        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(FORE_COLOR);

        for (int i = 0; i < points.size() - 1; i++) {
            if (Math.abs(points.get(i).getY() - points.get(i + 1).getY()) <= 20) {
                g.drawLine(points.get(i).getX(), points.get(i).getY(),
                        points.get(i + 1).getX(), points.get(i + 1).getY());
            }
        }
    }

    private void drawBridges(Graphics graphics) {

        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        points.forEach((p1) -> {
            points.stream().filter((p2) -> (p1.getY() == p2.getY())).forEachOrdered((p2) -> {
                g.setColor(FORE_COLOR);
                g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            });
        });

    }

    private void drawNodes(Graphics graphics) {
        points.forEach((point) -> point.draw(graphics, FORE_COLOR));
    }

}

