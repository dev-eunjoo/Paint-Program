import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Desc: Shape class for a paint program
 * Date: April 11, 2020
 *
 * @author Eunjoo Na
 **/
public abstract class Shape {

    /** Location x of the shape**/
    private double x;
    /** Location y of the shape**/
    private double y;
    /** width of the shape**/
    private double width;
    /** height of the shape**/
    private double height;

    private double lineWidth;
    /** line Color of the shape**/
    private Color lineColor;
    /** fill Color of the shape**/
    private Color fillColor;

    /**Shape constructor
     **/
    public Shape(double x,double y, double width, double height, double lineWidth) {
        this.x  = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.lineWidth = lineWidth;

    }

    /**get a line color of the shape
     **/
    public Color getLineColor() {
        return lineColor;
    }

    /**get a fill color of the shape
     **/
    public Color getFillColor() {
        return fillColor;
    }

    /**set a line color of the shape
     **/
    public void setLineColor(Color newColor) {
        lineColor = newColor;
    }

    /**set a fill color of the shape
     **/
    public void setFillColor(Color newColor) {
        fillColor = newColor;
    }

    /**get a x value of the shape
     **/
    public double getX() {
        return x;
    }

    /**set a x value of the shape
     **/
    public void setX(double x) {
        this.x = x;
    }

    /**get a y value of the shape
     **/
    public double getY() {
        return y;
    }

    /**set a y value of the shape
     **/
    public void setY(double y) {
        this.y = y;
    }

    /**get a width of the shape
     **/
    public double getWidth() {
        return width;
    }

    /**set a width of the shape
     **/
    public void setWidth(double width) {
        this.width = width;
    }

    /**get a height of the shape
     **/
    public double getHeight() {
        return height;
    }

    /**set a height of the shape
     **/
    public void setHeight(double height) {
        this.height = height;
    }

    /**get a lineWidth of the shape
     **/
    public double getLineWidth() {
        return lineWidth;
    }

    /**set a lineWidth of the shape
     **/
    public void setLineWidth(double height) {
        this.lineWidth = lineWidth;
    }

    /**draw a shape of the shape
     **/
    public abstract void draw(GraphicsContext gc);
}





