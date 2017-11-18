package application.component.objects.character.implement_character;

import application.component.objects.ImageManager;
import application.component.objects.character.PlayableCharacter;
import application.component.system.GameEnvironment;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

import java.awt.*;

public class Monster extends PlayableCharacter {
    private static String WAIT_IMAGE = "/images/monster.png";
    public static int DEFAULT_SPEED = 10;
    public static int MAX_SPEED = 100;
    public static int RANGE = 200;

    Point collisionRelativeDistance;

    private Circle range = new Circle(RANGE);

    public Monster(Point pos) {
        super(pos);
        position = pos;

        javafx.scene.image.Image waitImage = new Image(WAIT_IMAGE, GameEnvironment.getBlockScale(), GameEnvironment.getBlockScale(), true, true);
        imageManager.addImage(ImageManager.ObjectStatus.WAIT, waitImage);
        imageManager.showImage(ImageManager.ObjectStatus.WAIT);

        collisionRelativeDistance = new Point(-(int)(waitImage.getWidth() / 2), -(int)(waitImage.getHeight() / 2));

        updateImage();
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
        return imageManager.getImageView();
    }

    @Override
    public int getDefaultSpeed() {
        return DEFAULT_SPEED;
    }

    @Override
    public int getMaxSpeed() {
        return MAX_SPEED;
    }

    @Override
    public Point getSpeed() {
        return speed;
    }

    @Override
    protected Point getCollisionRelativeDistance() {
        return collisionRelativeDistance;
    }
}
