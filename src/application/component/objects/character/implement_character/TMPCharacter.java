package application.component.objects.character.implement_character;

import application.component.objects.character.PlayableCharacter;

import javafx.scene.Node;

import java.awt.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TMPCharacter extends PlayableCharacter {
    private Circle img = new Circle(10);
    private Circle range = new Circle(RANGE);
    public static int DEFAULT_SPEED = 10;
    public static int RANGE = 200;

    private Point speed = new Point(0, 0);

    public TMPCharacter(Point pos) {
        super(pos);
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

    public void move() {
        // position = position.setLocation(speed.x, speed.y);
        position.setLocation(position.x + speed.x, position.y + speed.y);

        updateImg();
    }
    
    public void jump() {
        
    }

    public void setSpeed(int xSpeed, int ySpeed) {
        speed.setLocation(speedCheck(xSpeed), speedCheck(ySpeed));
    }

    public int getXSpeed() {
        return speed.x;
    }

    public Node getImage() {
        return img;
    }

    public int getYSpeed() {
        return speed.y;
    }

    private int speedCheck(int speed) {
        if ( -DEFAULT_SPEED > speed ) { speed = -DEFAULT_SPEED; }
        else if ( DEFAULT_SPEED < speed ) { speed = DEFAULT_SPEED; }

        return speed;
    }

    @Override
    public void attack() {

    }

    @Override
    public int getDefaultSpeed() {
        return 0;
    }

    @Override
    public int getMaxSpeed() {
        return 0;
    }

    @Override
    public Point getSpeed() {
        return null;
    }

    @Override
    protected Point getCollisionRelativeDistance() {
        return null;
    }
}
