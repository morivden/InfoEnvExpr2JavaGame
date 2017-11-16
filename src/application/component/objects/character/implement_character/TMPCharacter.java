package application.component.objects.character.implement_character;

import application.component.objects.character.PlayableCharacter;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.EllipseBuilder;

public class TMPCharacter extends PlayableCharacter {
    private Circle img = new Circle(10);
    private Circle range = new Circle(RANGE);
    public static int DEFAULT_SPEED = 10;
    public static int RANGE = 200;

    private int x_speed = 0, y_speed = 0;

    public TMPCharacter(Point2D pos) {
        position = pos;
        range.setStrokeWidth(1);
        range.setFill(Color.TRANSPARENT);
        range.setStroke(Color.color(1.0, 0.3, 0.3, 0.5));
        updateImg();
    }

    private void updateImg() {
        img.setTranslateX(position.getX());
        img.setTranslateY(position.getY());
        range.setTranslateX(position.getX());
        range.setTranslateY(position.getY());
    }

    public Circle getRange() {
        return range;
    }

    public void move() {
        position = position.add(x_speed, y_speed);
        updateImg();
    }

    public void setSpeed(int x_speed, int y_speed) {
        this.x_speed = speedCheck(x_speed);
        this.y_speed = speedCheck(y_speed);
    }

    public int getX_speed() {
        return x_speed;
    }

    public Circle getImg() {
        return img;
    }

    public int getY_speed() {
        return y_speed;

    }

    private int speedCheck(int speed) {
        if ( -DEFAULT_SPEED > speed ) { speed = -DEFAULT_SPEED; }
        else if ( DEFAULT_SPEED < speed ) { speed = DEFAULT_SPEED; }

        return speed;
    }
}
