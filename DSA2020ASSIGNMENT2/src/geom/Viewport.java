/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geom;

/**
 * @author LTSACH
 */
public class Viewport {

    /**
     * @return the yMin
     */
    public double getyMin() {
        return yMin;
    }

    /**
     * @param yMin the yMin to set
     */
    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    /**
     * @return the yMax
     */
    public double getyMax() {
        return yMax;
    }

    /**
     * @param yMax the yMax to set
     */
    public void setyMax(double yMax) {
        this.yMax = yMax;
    }

    /**
     * @return the xMin
     */
    public double getxMin() {
        return xMin;
    }

    /**
     * @param xMin the xMin to set
     */
    public void setxMin(double xMin) {
        this.xMin = xMin;
    }

    /**
     * @return the xMax
     */
    public double getxMax() {
        return xMax;
    }

    /**
     * @param xMax the xMax to set
     */
    public void setxMax(double xMax) {
        this.xMax = xMax;
    }

    private double xMin, xMax;
    private double yMin, yMax;

    public Viewport(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public Viewport clone() {
        return new Viewport(this.xMin, this.xMax, this.yMin, this.yMax);
    }

    public void combine(Viewport vp) {
        this.xMin = Math.min(this.xMin, vp.xMin);
        this.xMax = Math.max(this.xMax, vp.xMax);
        this.yMin = Math.min(this.yMin, vp.yMin);
        this.yMax = Math.max(this.yMax, vp.yMax);
    }

    public void addPoint(Point2D point) {
        this.xMin = Math.min(this.xMin, point.getX());
        this.xMax = Math.max(this.xMax, point.getX());
        this.yMin = Math.min(this.yMin, point.getY());
        this.yMax = Math.max(this.yMax, point.getY());
    }
}
