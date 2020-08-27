import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    // TODO: Instance Variables for View Components and Model
    Canvas canvas = new Canvas(1200, 800);
    GraphicsContext gc;
    /** Buttons in the program **/
    Button circleBt, rectangleBt, clearBt, delete, selected, unselected, resetBt, front, back;
    /** TextField in the program **/
    TextField locationX, locationY, textWidth;
    Label  outline, fill, strokeWidth, instructions, selectedObject, shape;
    double firstX, firstY, lastX, lastY, width, height, lineWidth,  point1, point2, mouseX, mouseY,
            afterX, afterY, beforeX, beforeY;
    ColorPicker pickLine, pickFill;
    /** ArrayList object **/
    ArrayList<Shape> ShapeArrayList;

    // TODO: Private Event Handlers and Helper Methods

    private void delete(ActionEvent m) {
        List<Shape> remove = new ArrayList<Shape>();// ArrayList remove to remove an object
        for (Shape a : ShapeArrayList) {
            double x = a.getX();
            double y = a.getY();
            double width = a.getWidth();
            double height = a.getHeight();
            if ((mouseX >= x && mouseX <= (x + width)) &&
                    (mouseY >= y && mouseY <= (y + height))) {
                remove.add(a);
            }
        }

        ShapeArrayList.removeAll(remove);
        clearScreen();
        drawing();
        silverButtons();
    }

    private void newCircle(ActionEvent m) {
        try {
            Pressed();
            lineWidth = Double.parseDouble(textWidth.getText());
            canvas.setOnMouseReleased(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    lastX = event.getX();
                    lastY = event.getY();
                    setShape(firstX, firstY, lastX, lastY);

                    Shape d = new Circle(point1, point2, width, height, lineWidth);
                    d.setLineColor(pickLine.getValue());
                    d.setFillColor(pickFill.getValue());

                    ShapeArrayList.add(d);// add a rectangle in the Array list
                    drawing();// draw a Array list
                }
            });
        } catch (NumberFormatException t) {//NumberFormatException alert
            new Alert(Alert.AlertType.WARNING, "You should enter the width with number").showAndWait();
        }
    }

    private void newRectangle(ActionEvent m) {
        try {
            Pressed();
            lineWidth = Double.parseDouble(textWidth.getText());
            canvas.setOnMouseReleased(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    lastX = event.getX();
                    lastY = event.getY();
                    setShape(firstX, firstY, lastX, lastY);
                    Shape d = new Rectangle(point1, point2, width, height, lineWidth);
                    d.setLineColor(pickLine.getValue());
                    d.setFillColor(pickFill.getValue());

                    ShapeArrayList.add(d);// add a rectangle in the Array list
                    drawing();// draw a Array list
                }
            });
        } catch (NumberFormatException t) {//NumberFormatException alert
            new Alert(Alert.AlertType.WARNING, "You should enter the width with number").showAndWait();
        }
    }

    private void resetColor(ActionEvent e) {
        for (Shape a : ShapeArrayList) {
            a.setLineColor(pickLine.getValue());
            a.setFillColor(pickFill.getValue());
            a.draw(gc);
        }
    }

    private void clearShape(ActionEvent e) {
        ShapeArrayList.clear();
        clearScreen();
    }

    /***drawing ShapeArrayList on the screen
     **/
    private void drawing() {
        for (Shape a : ShapeArrayList) {
            a.draw(gc);
        }
    }


    public void setShape(double a, double b, double c, double d) {
        width = 0;
        height = 0;
        point1 = firstX;
        point2 = firstY;
        if (a > c) {
            width = a - c;
            point1 = lastX;
            point2 = lastY;
        } else if (a == c) {
            width = height;
        } else {
            width = c - a;
        }
        if (b > d) {
            point2 = lastY;
            height = b - d;
        } else if (b == d) {
            height = width;
        } else {
            height = d - b;
            point2 = firstY;
        }
    }

    public void Pressed() {
        canvas.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                firstX = event.getX();
                firstY = event.getY();
            }
        });
    }

    /***display a default screen
     **/
    private void clearScreen() {
        gc.setFill(Color.rgb(204, 238, 255));
        gc.fillRect(0, 0, 1200, 600);
        gc.setFill(Color.rgb(153, 204, 0));
        gc.fillRect(0, 600, 1200, 200);
    }


    private void moving(){
        canvas.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                beforeX = event.getSceneX();
                beforeY = event.getSceneY();
            }
        });
        canvas.setOnMouseReleased((m)-> {//when the mouse is released
            if(m.getButton() == MouseButton.SECONDARY) {
                afterX = m.getSceneX();
                afterY = m.getSceneY();

                for (Shape a : ShapeArrayList) {
                    if ((beforeX >= a.getX() && beforeX <= (a.getX() + a.getWidth())) &&
                            (beforeY >= a.getY() && beforeY <= (a.getY() + a.getHeight()))) {

                        a.setX(afterX - a.getWidth() / 2);
                        a.setY(afterY - a.getHeight() / 2);

                        silverButtons();
                        clearScreen();
                        drawing();
                        break;
                    }
                }
            }
        });
    }

    private void toFront(ActionEvent e) {
        for (Shape a : ShapeArrayList) {
            if ((mouseX >= a.getX() && mouseX <= (a.getX() + a.getWidth())) &&
                    (mouseY >= a.getY() && mouseY <= (a.getY() + a.getHeight()))) {
                int num = ShapeArrayList.indexOf(a);
                ShapeArrayList.add(a);
                ShapeArrayList.remove(num);
                silverButtons();
                clearScreen();
                drawing();
                break;
            }
        }
    }

    private void toBack(ActionEvent e){
        for (Shape a : ShapeArrayList) {
            if ((mouseX >= a.getX() && mouseX <= (a.getX() + a.getWidth())) &&
                    (mouseY >= a.getY() && mouseY <= (a.getY() + a.getHeight()))) {
                int num = ShapeArrayList.indexOf(a);
                ShapeArrayList.add(0,a);
                ShapeArrayList.remove(num+1);
                silverButtons();
                clearScreen();
                drawing();
                break;
            }
        }
    }

    private void blackButtons(){
        delete.setStyle("-fx-font: 17 arial;-fx-text-fill:black;");
        front.setStyle("-fx-font: 17 arial;-fx-text-fill:black;");
        back.setStyle("-fx-font: 17 arial;-fx-text-fill:black;");
    }

    private void silverButtons(){
        delete.setStyle("-fx-font: 17 arial;-fx-text-fill:silver;");
        front.setStyle("-fx-font: 17 arial;-fx-text-fill:silver;");
        back.setStyle("-fx-font: 17 arial;-fx-text-fill:silver;");
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1200, 800); // set the size here
        stage.setTitle("Paint Program"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model
        ShapeArrayList = new ArrayList<>();

        // 2. Create the GUI components
        instructions = new Label("  INSTRUCTIONS\n\n- Left Mouse click and drag to draw a new object\n" +
                "- Right Mouse click and drag to move selected object\n- Delete button click and click the object " +
                "want to delete" +
                "\n- Delete/Move to Front-Back using Buttons");

        selected = new Button("Select");
        unselected = new Button("Unselect");
        delete = new Button("Delete");

        outline = new Label("Outline");
        fill = new Label("Fill");
        strokeWidth = new Label("Stroke Width");
        textWidth = new TextField("");
        pickLine = new ColorPicker();
        pickFill = new ColorPicker();
        circleBt = new Button("Circle");
        rectangleBt = new Button("Rectangle");
        clearBt = new Button("All Clear");
        resetBt = new Button("Reset Shape Colors");
        front = new Button("To front");
        back = new Button("To back");
        selectedObject = new Label("---- Selected Object ---");
        shape = new Label("Shape");

        // 3. Add components to the root
        root.getChildren().addAll(canvas, circleBt, rectangleBt, clearBt, resetBt,delete,
                outline, fill, strokeWidth, pickLine, pickFill, textWidth, instructions, front, back, selectedObject, shape);

        // 4. Configure the components (colors, fonts, size, location)
        outline.relocate(30, 620);
        fill.relocate(170, 620);
        strokeWidth.relocate(310, 620);

        pickLine.relocate(30, 650);
        pickFill.relocate(170, 650);

        textWidth.setPrefColumnCount(8);
        textWidth.relocate(310, 650);

        circleBt.relocate(30, 730);
        rectangleBt.relocate(110, 730);

        resetBt.relocate(460, 640);
        selected.relocate(300, 730);
        unselected.relocate(380, 730);
        delete.relocate(400, 730);
        front.relocate(500,730);
        back.relocate(600,730);
        clearBt.relocate(640,640);

        instructions.relocate(770, 620);
        selectedObject.relocate(450,700);
        shape.relocate(30, 700);


        outline.setStyle("-fx-font: 17 arial;");
        fill.setStyle("-fx-font: 17 arial;");
        strokeWidth.setStyle("-fx-font: 17 arial;");
        delete.setStyle("-fx-font: 17 arial;-fx-text-fill:silver;");
        circleBt.setStyle("-fx-font: 17 arial;");
        rectangleBt.setStyle("-fx-font: 17 arial;");
        clearBt.setStyle("-fx-font: 16 arial;");
        instructions.setStyle("-fx-font: 17 arial;");
        resetBt.setStyle("-fx-font: 16 arial;");
        front.setStyle("-fx-font: 17 arial;-fx-text-fill:silver;");
        back.setStyle("-fx-font: 17 arial;-fx-text-fill:silver;");
        selectedObject.setStyle("-fx-font: 17 arial;");
        shape.setStyle("-fx-font: 17 arial;");

        // 5. Add Event Handlers and do final setup
        circleBt.setOnAction(this::newCircle);
        rectangleBt.setOnAction(this::newRectangle);
        //delete.setOnAction(this::deleteObject);
        clearBt.setOnAction(this::clearShape);
        resetBt.setOnAction(this::resetColor);


        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.rgb(204, 238, 255));
        gc.fillRect(0, 0, 1200, 600);
        gc.setFill(Color.rgb(153, 204, 0));
        gc.fillRect(0, 600, 1200, 200);

        canvas.setOnMouseClicked((e)-> {//when the mouse is released
            if(e.getButton() == MouseButton.SECONDARY) {

                blackButtons();
                mouseX = e.getSceneX();
                mouseY = e.getSceneY();
                delete.setOnAction(this::delete);
                front.setOnAction(this::toFront);
                back.setOnAction(this::toBack);
                moving();
            }
        });

        drawing();

        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}

