package application.component.objects.character.implement_character;

import application.component.objects.character.PlayableCharacter;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.*;

public class Monster extends PlayableCharacter {
    private Circle img = new Circle(10);
    private Circle range = new Circle(RANGE);
    public static int DEFAULT_SPEED = 10;
    public static int RANGE = 200;

    public Monster(Point pos) {
        super(pos);
        position = pos;
        range.setStrokeWidth(1);
        range.setFill(javafx.scene.paint.Color.TRANSPARENT);
        range.setStroke(Color.color(1.0, 0.3, 0.3, 0.5));
        updateImage();
    }

    private void updateImage() {
        img.setTranslateX(position.x);
        img.setTranslateY(position.y);
        range.setTranslateX(position.x);
        range.setTranslateY(position.y);
    }

    @Override
    public void move() {
        position.setLocation(position.x + speed.x, position.y + speed.y);
        updateImage();
    }

    @Override
    public void attack() {

    }

    @Override
    public Node getImage() {
        return new Group(img, range);
    }

    @Override
    public int getDefaultSpeed() {
        return DEFAULT_SPEED;
    }

    @Override
    public Point getSpeed() {
        return speed;
    }
}
