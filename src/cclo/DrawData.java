/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cclo;

import java.util.*;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import javax.swing.JFrame;

/**
 *
 * @author Sien
 */
public class DrawData extends JFrame {

    //LinkedList<ArrayList<Integer>> list = new LinkedList<ArrayList<Integer>>();
    Queue<int[]> list = new LinkedList<>(); //陣列佇列，每個陣列為編碼過後的1024大小聲音頻率
    //LinkedList<Integer> list = new LinkedList<>();
    String str = "";
    int out[] = new int[1024];
    int cnt = 0;
    Color[] colors = new Color[256];

    public DrawData() {
        super("視窗");
        Container cp = this.getContentPane();

        cp.setBackground(Color.WHITE); //背景設為白色
        //this.setLocation(200, 300);
        this.setBounds(50, 50, 1250, 1250); //設置視窗大小
        //this.setSize(300, 300);
        this.setLayout(getLayout());
        this.setVisible(false);
        for (int i = 0; i < 256; i++) { //先將顏色寫入陣列，灰階為rgb三色相同
            colors[i] = new Color(i, i, i);
        }
    }

    public void putdata(int encodeFreq[]) {
        cnt++;
        if (true) { //先設立不顯示
            return;
        }
        if (cnt < 10) { //少於10筆資料，則不顯示
            return;
        } else {
            cnt = 0;
        }
        int i, j;
        //ArrayList<Integer> empty = new ArrayList<>();

        /*for (i = 0; i < 1024; i++) {
           out[i] = encodeFreq[i];
          }*/
        synchronized (list) {
            list.offer(encodeFreq);
            while (list.size() > 100) { //只容納近100筆聲音陣列
                list.poll();
            }
        }
        repaint();
        //System.out.println(list.getLast().get(5));
    }

    /*public void output() {
        Frame frame = new Frame("Demo");
        frame.setSize(800, 800);
        testframe canvas = new testframe();
        frame.add(canvas, BorderLayout.CENTER);
        frame.setVisible(true);
    }*/
    public void paint(Graphics g) {
        super.paint(g);//畫出視窗背景色
        g.clearRect(0, 0, 1250, 1250);
        Graphics gr = g;
        int x = 10; //初始畫布x軸
        int y = 30; //初始畫布y軸
        // g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 8));
        synchronized (list) {
            for (int[] arr : list) {
                x = 10;
                for (int i = 0; i < 1024; i++) {
                    int gray = arr[i]; //arr[]陣列內為編碼過聲音頻率，將gray依序設為陣容列內容
                    // Color c = new Color(gray, gray, gray);
                    gr.setColor(colors[gray]);  //將gary的數值用來設立灰階
                    gr.fillOval(x, y, 2, 2); //依照上面設立的顏色，畫出大小2*2的圓形
                    x += 2;
                }
                y += 2;
            }
//             ArrayList<Integer> out = list.getFirst();
//            while (!list.isEmpty()) {
//                //if (i < 1024) {
//                if (!out.isEmpty()) {
//                    int gray = out.get(0);
//                    Color c = new Color(gray, gray, gray);
//                    gr.setColor(c);
//                    gr.fillOval(x, y, 5, 5);
//                    x += 5;
//                    out.remove(0);
//                } else {
//                    //i=0;
            //                    y += 20;
            //                    list.remove(); x = 10;
//                   
//                }
//            }

        }
    }
}
