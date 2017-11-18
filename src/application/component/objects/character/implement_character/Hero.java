package application.component.objects.character.implement_character;

import application.component.objects.character.PlayableCharacter;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.*;

public class Hero extends PlayableCharacter {
    private Circle img = new Circle(10);
    public static int DEFAULT_SPEED = 10;

    public Hero(Point pos) {
        super(pos);

        img.setFill(Color.RED);

        updateImage();
    }

    private void updateImage() {
        img.setTranslateX(position.x);
        img.setTranslateY(position.y);
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
        return img;
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
