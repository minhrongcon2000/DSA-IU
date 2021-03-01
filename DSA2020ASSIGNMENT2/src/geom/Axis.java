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
public class Axis extends GeomObject {
    public static final int NUM_GRAPHS = 20;

    /**
     * @return the viewport
     */
    public Viewport getViewport() {
        return viewport;
    }

    private Viewport viewport;
    /*Study List => use your own list data structure to manager graphs*/
    Graph[] graphs = new Graph[NUM_GRAPHS]; //suport 20 graphs max
    int nGraph = 0;

    public Axis(double xMin, double xMax, double yMin, double yMax) {
        this.viewport = new Viewport(xMin, xMax, yMin, yMax);
    }

    public Axis(Viewport vp) {
        this.viewport = vp.clone();
    }

    private void _addGraph(Graph graph) {
        if (this.nGraph >= NUM_GRAPHS) return; //do nothing
        this.graphs[this.nGraph++] = graph;
        this.getViewport().combine(graph.getViewport());
    }

    public void addGraph(Graph graph) {
        this._addGraph(graph);
    }

    public void addGraph(Graph graph, Color color) {
        this._addGraph(graph);
        graph.setEdgeColor(color);
    }

    public void addGraph(Graph graph, Color color, int line_weight) {
        this._addGraph(graph);
        graph.setLineWeight(line_weight);
        graph.setEdgeColor(color);
    }

    @Override
    public void draw(Graphics g, SpaceMapping mapper) {
        drawXAxis(g, mapper);
        drawYAxis(g, mapper);
        for (int idx = 0; idx < this.nGraph; idx++) {
            this.graphs[idx].draw(g, mapper);
        }
    }

    /* FontMetrics glossary:
    http://www.myfirstfont.com/glossary.html
    */
    protected void drawXAxis(Graphics g, SpaceMapping mapper) {
        Graphics2D g2 = (Graphics2D) g;

        //Draw horizontal line (thick) at the bottom (yMin)
        Point2D devXLeft = mapper.logic2Device(new Point2D(this.getViewport().getxMin(),
                this.getViewport().getyMin()));
        Point2D devXRight = mapper.logic2Device(new Point2D(this.getViewport().getxMax(),
                this.getViewport().getyMin()));
        int x1 = (int) devXLeft.getX(), y1 = (int) devXLeft.getY();
        int x2 = (int) devXRight.getX(), y2 = (int) devXRight.getY();

        g.setColor(Color.black);
        Stroke style = new BasicStroke(2); //thickness = 2
        g2.setStroke(style);
        g2.drawLine(x1, y1, x2, y2);

        //Draw ticks
        double step = 5 * mapper.stepx10(); //step-size <=> 50 pixels
        int nTicks = (int) ((this.getViewport().getxMax() - this.getViewport().getxMin()) / step) + 1;
        double xTick = this.getViewport().getxMin();
        String sTick = "";
        for (int idx = 0; idx < nTicks; idx++) {
            sTick = String.format("%5.2f", xTick);

            Point2D devTickPos = mapper.logic2Device(new Point2D(xTick, this.getViewport().getyMin()));
            x1 = (int) devTickPos.getX();
            y1 = (int) devTickPos.getY();
            FontMetrics fm = g.getFontMetrics();
            int sx = x1 - fm.stringWidth(sTick) / 2; //"-" means shifting left
            int sy = y1 + fm.getHeight();
            //draw string in black
            g.setColor(Color.black);
            g.drawString(sTick, sx, sy);
            x2 = x1;
            y2 = y1 + 5;
            //draw tick in blue
            g.setColor(Color.blue);
            style = new BasicStroke(1);
            g2.setStroke(style);
            g2.drawLine(x1, y1, x2, y2);

            xTick += step;
        }


    }

    protected void drawYAxis(Graphics g, SpaceMapping mapper) {
        Graphics2D g2 = (Graphics2D) g;
        //Draw vertical line (thick) on the leftmost (xMin)
        Point2D devYTop = mapper.logic2Device(new Point2D(this.getViewport().getxMin(),
                this.getViewport().getyMax()));
        Point2D devYBottom = mapper.logic2Device(new Point2D(this.getViewport().getxMin(),
                this.getViewport().getyMin()));
        int x1 = (int) devYTop.getX(), y1 = (int) devYTop.getY();
        int x2 = (int) devYBottom.getX(), y2 = (int) devYBottom.getY();

        g.setColor(Color.black);
        Stroke style = new BasicStroke(2);
        g2.setStroke(style);
        g2.drawLine(x1, y1, x2, y2);

        double step = 5 * mapper.stepy10(); //step-size <=> 50 pixels
        int nTicks = (int) ((this.getViewport().getyMax() - this.getViewport().getyMin()) / step) + 1;
        double yTick = this.getViewport().getyMin();
        String sTick = "";

        for (int idx = 0; idx < nTicks; idx++) {
            sTick = String.format("%5.2f", yTick);

            Point2D sPos = mapper.logic2Device(new Point2D(this.getViewport().getxMin(), yTick));
            int x = (int) sPos.getX(), y = (int) sPos.getY();
            FontMetrics fm = g.getFontMetrics();
            int sx = x - fm.stringWidth(sTick) - 10;
            int sy = y + fm.getAscent() / 2;
            //draw string in black
            g.setColor(Color.black);
            g.drawString(sTick, sx, sy);
            x2 = x - 10;
            y2 = y;
            //draw tick in blue
            g.setColor(Color.blue);
            style = new BasicStroke(1);
            g2.setStroke(style);
            g2.drawLine(x, y, x2, y2);

            yTick += step;
        }
    }

}
