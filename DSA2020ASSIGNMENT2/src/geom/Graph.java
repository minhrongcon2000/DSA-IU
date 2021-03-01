/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geom;

import java.awt.*;

/**
 * @author LTSACH
 */
public class Graph extends GeomObject {

    /**
     * @return the mode
     */
    public GraphMode getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(GraphMode mode) {
        this.mode = mode;
    }

    public enum GraphMode {
        LINE,
        SCATTER
    }

    ;
    protected Viewport viewport = null;
    private Point2D[] points = null;
    private GraphMode mode = GraphMode.LINE;

    public Graph(Point2D[] points, double xMin, double xMax, double yMin, double yMax) {
        this.viewport = new Viewport(xMin, xMax, yMin, yMax);
        this.points = points;
    }

    public Graph(Point2D[] points, double xMin, double xMax, double yMin, double yMax, Color color) {
        this.viewport = new Viewport(xMin, xMax, yMin, yMax);
        this.points = points;
        this.edgeColor = color;
    }

    private void copyPoints(Point2D[] points) {
        this.points = points;
        //update viewport
        this.viewport = new Viewport(points[0].getX(), points[0].getX(),
                points[0].getY(), points[0].getY()
        );
        for (int idx = 0; idx < points.length; idx++)
            this.viewport.addPoint(points[idx]);
    }

    public Graph(Point2D[] points) {
        copyPoints(points);
    }

    public Graph(Point2D[] points, Color color) {
        copyPoints(points);
        this.edgeColor = color;
    }

    @Override
    public void draw(Graphics g, SpaceMapping mapper) {
        Graphics2D g2 = (Graphics2D) g;
        //
        if (this.mode == GraphMode.LINE) {
            if (this.points == null) return;
            int[] x = new int[this.points.length];
            int[] y = new int[this.points.length];
            for (int idx = 0; idx < this.points.length; idx++) {
                Point2D devPoint = mapper.logic2Device(this.points[idx]);
                x[idx] = (int) devPoint.getX();
                y[idx] = (int) devPoint.getY();
            }
            g2.setColor(this.getEdgeColor());
            Stroke style = new BasicStroke(this.getLineWeight());
            g2.setStroke(style);
            g2.drawPolyline(x, y, x.length);
        } else if (this.mode == GraphMode.SCATTER) {
            for (int idx = 0; idx < this.points.length; idx++) {
                this.points[idx].setEdgeColor(this.getEdgeColor());
                this.points[idx].setFaceColor(this.getEdgeColor());
                this.points[idx].draw(g, mapper);
            }
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public static Graph sin(double a, double xMin, double xMax, double step) {
        int N = (int) ((xMax - xMin) / step) + 1;
        Point2D[] points = new Point2D[N];
        double T = xMax - xMin;
        double x = xMin;
        for (int idx = 0; idx < N; idx++) {
            double y = a * Math.sin(2 * Math.PI * (1.0 / T) * (x - xMin));
            points[idx] = new Point2D(x, y);
            x += step;
        }
        return new Graph(points);
    }

    public static Graph quadratic(double a, double b, double c, double xMin, double xMax, double step) {
        int N = (int) ((xMax - xMin) / step) + 1;
        Point2D[] points = new Point2D[N];
        double x = xMin;
        for (int idx = 0; idx < N; idx++) {
            double y = a * x * x + b * x + c;
            points[idx] = new Point2D(x, y);
            x += step;
        }
        return new Graph(points);
    }

    public Viewport getViewport() {
        return this.viewport.clone();
    }
}
