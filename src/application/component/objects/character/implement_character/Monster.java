package application.component.objects.character.implement_character;

import application.component.objects.DammyCollisionObject;
import application.component.objects.ImageManager;
import application.component.objects.RectangleCollisionObject;
import application.component.objects.character.PlayableCharacter;
import application.component.system.GameEnvironment;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.*;

public class Monster extends PlayableCharacter {
    private static String WAIT_IMAGE = "/images/monster.png";
    public static int DEFAULT_SPEED = 10;
    public static int MAX_SPEED = 100;
    public static int DEFAULT_RANGE = 200;
    public static int DEFAULT_HP = 10;


    Point collisionRelativeDistance;

    private Circle range = new Circle(DEFAULT_RANGE);

    public Monster(Point pos) {
        super(pos);

        hp = DEFAULT_HP;

        //== イメージ関連
        javafx.scene.image.Image waitImage = new Image(WAIT_IMAGE, GameEnvironment.getBlockScale(), GameEnvironment.getBlockScale(), true, true);
        imageManager.addImage(ImageManager.ObjectStatus.WAIT, waitImage);
        imageManager.showImage(ImageManager.ObjectStatus.WAIT);

        //= 索敵範囲関連
        range.setStroke(Color.color(0, 0, 0.8, 0.5));
        range.setFill(Color.TRANSPARENT);
        range.setCenterX(pos.x);
        range.setCenterY(pos.y);

        collisionRelativeDistance = new Point(-(int)(waitImage.getWidth() / 2), -(int)(waitImage.getHeight() / 2));

        //== 当たり判定関連
        RectangleCollisionObject rectCO = new RectangleCollisionObject(position.x + collisionRelativeDistance.x,
                position.y + collisionRelativeDistance.y, (int)waitImage.getWidth(), (int) waitImage.getHeight());
        collisionObject = rectCO;

        updateImage();
    }

    @Override
    public void move() {
        position.setLocation(position.x + speed.x, position.y + speed.y);
        collisionObject.transfer( position.x + collisionRelativeDistance.x, position.y + collisionRelativeDistance.y);
        range.setCenterX(position.x);
        range.setCenterY(position.y);
        updateImage();
    }

    @Override
    public void attack() {

    }

    @Override
    public Node getImage() {
        return new Group(imageManager.getImageView(), range);
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
    public int getRange() {
        return Monster.DEFAULT_RANGE;
    }

    @Override
    public void disable() {

    }

    @Override
    protected Point getCollisionRelativeDistance() {
        return collisionRelativeDistance;
    }
}
