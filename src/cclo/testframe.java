/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cclo;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Sien
 */
public class testframe extends JFrame {

    String out = "";
    List<String> list = new LinkedList<>();

    public testframe() {
    }

    public void putdata(String str) {
        out = str;

        synchronized (list) {
            if (list.size() < 100) {
                list.add(out);
            } else {
                list.remove(1);
                list.add(out);
            }
        }
        repaint();
    }

    public testframe(String x) {
        out = x;
    }

    public void output() {
        Frame frame = new Frame("Demo");
        frame.setSize(800, 800);
        testframe canvas = new testframe();
        frame.add(canvas, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, 1000, 800);
        int y = 30;
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 8));
        synchronized (list) {
            for (String str : list) {
                g.drawString(str, 10, y);
                y += 5;
            }
        }

    }
}
