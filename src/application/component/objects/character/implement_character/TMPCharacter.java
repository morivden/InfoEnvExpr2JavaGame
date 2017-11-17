package application.component.objects.character.implement_character;

import application.component.objects.character.PlayableCharacter;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class TMPCharacter extends PlayableCharacter {
    private Circle img = new Circle(10);
    public static int DEFAULT_SPEED = 10;

    private Point2D speed;

    public TMPCharacter(Point2D pos) {
        position = pos;
        updateImg();
    }

    private void updateImg() {
        img.setTranslateX(position.getX());
        img.setTranslateY(position.getY());
    }

    public void move() {
        position = position.add(speed.getX(), speed.getY());
        updateImg();
    }

    public void setSpeed(int x_speed, int y_speed) {
        this.speed.add(speedCheck(x_speed), speedCheck(y_speed));
    }

    public int getX_speed() {
        return (int)speed.getX();
    }

    public Circle getImg() {
        return img;
    }

    public int getY_speed() {
        return (int)speed.getY();

    }

    private int speedCheck(int speed) {
        if ( -DEFAULT_SPEED > speed ) { speed = -DEFAULT_SPEED; }
        else if ( DEFAULT_SPEED < speed ) { speed = DEFAULT_SPEED; }

        return speed;
    }
}
