import javafx.scene.canvas.GraphicsContext;

/**
 * Desc: Rectangle class for a paint program
 * Date: April 11, 2020
 *
 * @author Eunjoo Na
 **/


public class Rectangle extends Shape {

    public Rectangle(double x, double y, double width, double height, double lineWidth) {
        super(x,y,width,height,lineWidth);
    }


    /**draw a shape of the Rectangle
     **/
    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(getLineColor());
        gc.setFill(getFillColor());
        gc.setLineWidth(getLineWidth());
        gc.strokeRect(getX(), getY(), getWidth(), getHeight());
        gc.fillRect(getX(), getY(), getWidth(), getHeight());
    }

}
