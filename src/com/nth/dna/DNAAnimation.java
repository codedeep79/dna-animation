/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nth.dna;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingUtilities;

/**
 *
 * Created by HAU TRUNG NGUYEN <haunt.hcm2015@gmail.com> on Nov 18, 2021
 */
public class DNAAnimation extends JFrame {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 800 / 16 * 9;

    private DNAAnimation() {
        ArrayList<Point> points = createPoints();
        Animation animation = createAnimation(points);
        setWindowProperties();
        startAnimation(animation);
    }

    private ArrayList<Point> createPoints() {
        ArrayList<Point> points = new ArrayList<>();

        for (int i = 0; i < 18; i++) {
            points.add(new Point(WIDTH / 2, 40 + (i * 20), i * 5));
        }

        for (int i = 0; i < 18; i++) {
            points.add(new Point(WIDTH / 2, 40 + (i * 20), 30 + (i * 5)));
        }

        return points;
    }

    private Animation createAnimation(ArrayList<Point> points) {
        Animation animation = new Animation(points);
        Container cp = getContentPane();
        cp.add(animation);
        animation.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        return animation;
    }

    private void setWindowProperties() {
        setResizable(false);
        pack();
        setTitle("DNA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void startAnimation(Animation animation) {
        Thread th = new Thread(animation);
        th.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DNAAnimation());
    }

}
