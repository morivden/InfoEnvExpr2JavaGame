package application.component.objects.character.implement_character;

import application.component.objects.character.PlayableCharacter;
import com.sun.javafx.geom.Point2D;

import javafx.scene.Node;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TMPCharacter extends PlayableCharacter {
    private Circle img = new Circle(10);
    private Circle range = new Circle(RANGE);
    public static int DEFAULT_SPEED = 10;
    public static int RANGE = 200;

    private Point2D speed = new Point2D(0, 0);

    public TMPCharacter(Point2D pos) {
        position = pos;
        range.setStrokeWidth(1);
        range.setFill(Color.TRANSPARENT);
        range.setStroke(Color.color(1.0, 0.3, 0.3, 0.5));
        updateImg();
    }

    private void updateImg() {
        img.setTranslateX(position.x);
        img.setTranslateY(position.y);
        range.setTranslateX(position.x);
        range.setTranslateY(position.y);
    }

    public Circle getRange() {
        return range;
    }

    public void move() {
        // position = position.setLocation(speed.x, speed.y);
        position.setLocation(position.x + speed.x, position.y + speed.y);

        updateImg();
    }

    public void setSpeed(int x_speed, int y_speed) {
        speed.setLocation(speedCheck(x_speed), speedCheck(y_speed));
    }

    public int getX_speed() {
        return (int)speed.x;
    }

    public Node getImg() {
        return img;
    }

    public int getY_speed() {
        return (int)speed.y;
    }

    private int speedCheck(int speed) {
        if ( -DEFAULT_SPEED > speed ) { speed = -DEFAULT_SPEED; }
        else if ( DEFAULT_SPEED < speed ) { speed = DEFAULT_SPEED; }

        return speed;
    }
}
