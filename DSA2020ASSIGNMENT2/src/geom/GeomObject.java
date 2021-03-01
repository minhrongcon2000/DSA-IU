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
public abstract class GeomObject {

    /**
     * @return the edgeColor
     */
    public Color getEdgeColor() {
        return edgeColor;
    }

    /**
     * @param edgeColor the edgeColor to set
     */
    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }

    /**
     * @return the faceColor
     */
    public Color getFaceColor() {
        return faceColor;
    }

    /**
     * @param faceColor the faceColor to set
     */
    public void setFaceColor(Color faceColor) {
        this.faceColor = faceColor;
    }

    /**
     * @return the line_weight
     */
    public int getLineWeight() {
        return line_weight;
    }

    /**
     * @param line_weight the line_weight to set
     */
    public void setLineWeight(int line_weight) {
        this.line_weight = line_weight;
    }

    protected Color edgeColor;
    protected Color faceColor;
    protected int line_weight = 1;

    public GeomObject() {
        faceColor = new Color(20, 150, 20);
        edgeColor = new Color(150, 20, 20);
    }

    public abstract void draw(Graphics g, SpaceMapping mapper);
}
