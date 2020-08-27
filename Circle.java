import javafx.scene.canvas.GraphicsContext;

/**
 * Desc: Circle class for a paint program
 * Date: April 11, 2020
 *
 * @author Eunjoo Na
 **/
public class Circle extends Shape {


    public Circle(double x, double y, double width, double height, double lineWidth) {
        super(x,y,width,height,lineWidth);
    }



    /**draw a shape of the circle
     **/
    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(getLineColor());
        gc.setFill(getFillColor());
        gc.setLineWidth(getLineWidth());
        gc.strokeOval(getX(), getY(), getWidth(), getHeight());
        gc.fillOval(getX(), getY(), getWidth(), getHeight());
    }


}
