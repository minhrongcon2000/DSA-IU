/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import geom.Point2D;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


class Rectangle {
    private Point2D lt, rb;

    public Rectangle(Point2D lt, Point2D rb) {
        double left = Math.min(lt.getX(), rb.getX());
        double right = Math.max(lt.getX(), rb.getX());
        double top = Math.max(lt.getY(), rb.getY());
        double bottom = Math.min(lt.getY(), rb.getY());
        this.lt = new Point2D(left, top);
        this.rb = new Point2D(right, bottom);
    }

    public double area() {
        double w = this.rb.getX() - this.lt.getX();
        double h = this.lt.getY() - this.rb.getY();
        return w * h;
    }

    public String toString() {
        String info = String.format("R:LT(%6.2f, %6.2f);RB(%6.2f, %6.2f)",
                this.lt.getX(), this.lt.getY(),
                this.rb.getX(), this.lt.getY());
        return info;
    }
}

public class ListApp {
    public static void main(String[] args) {
        List<Rectangle> list = new LinkedList<>();
        int N = 10;

        //Generate
        Random gen = new Random();
        gen.setSeed(0);
        for (int idx = 0; idx < N; idx++) {
            double left = gen.nextDouble();
            double top = gen.nextDouble();
            double right = gen.nextDouble();
            double bottom = gen.nextDouble();
            Rectangle rect = new Rectangle(new Point2D(left, top), new Point2D(right, bottom));
            list.add(rect);
        }
        //Print
        for (int idx = 0; idx < list.size(); idx++) {
            Rectangle rect = list.get(idx);
            String info = String.format("%3d %25s %6.2f", idx, rect, rect.area());
            System.out.println(info);
        }
        //remove rectangle that its area >= 0.1
        double threshold = 0.1;
        /*
        for(int idx=0; idx < list.size(); idx++){
            Rectangle rect = list.get(idx);
            if(rect.area() <= threshold) list.remove(idx);
        }
        */

        Iterator<Rectangle> it = list.iterator();
        while (it.hasNext()) {
            Rectangle rect = it.next();
            if (rect.area() <= threshold) it.remove();
        }
        //Print
        System.out.println("==================");
        for (int idx = 0; idx < list.size(); idx++) {
            Rectangle rect = list.get(idx);
            String info = String.format("%3d %25s %6.2f", idx, rect, rect.area());
            System.out.println(info);
        }
    }

}
