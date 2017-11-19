package application.component.objects.character.implement_character;

import application.component.objects.ImageManager;
import application.component.objects.RectangleCollisionObject;
import application.component.objects.character.PlayableCharacter;
import application.component.system.GameEnvironment;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.awt.*;


public class Hero extends PlayableCharacter {
    private static String WAIT_IMAGE = "/images/hero.png";
    public static int DEFAULT_SPEED = 10;
    public static int MAX_SPEED = 100;

    private Rectangle collRect;

    private Point collisionRelativeDistance;

    public Hero(Point pos) {
        super(pos);

        //===  イメージ関連
        Image waitImage = new Image(WAIT_IMAGE, GameEnvironment.getBlockScale(), GameEnvironment.getBlockScale(), true, true);
        imageManager.addImage(ImageManager.ObjectStatus.WAIT, waitImage);
        imageManager.showImage(ImageManager.ObjectStatus.WAIT);

        //=== 衝突物体の相対距離算出
        collisionRelativeDistance = new Point(-(int)(waitImage.getWidth() / 2), -(int)(waitImage.getHeight() / 2));

        //=== 衝突物体関連
        RectangleCollisionObject rectCO = new RectangleCollisionObject(position.x + collisionRelativeDistance.x,
                position.y + collisionRelativeDistance.y, (int)waitImage.getWidth(), (int) waitImage.getHeight());
//        rectCO.addEvent((o, e, a) -> System.out.println("  ヒーロー衝突"));
        collisionObject = rectCO;

        // TODO 消すやつ
        collRect = new Rectangle(rectCO.getRectangle().x, rectCO.getRectangle().y, rectCO.getRectangle().width, rectCO.getRectangle().height);
        collRect.setFill(Color.color(0, 0, 0.5, 0.5));

        updateImage();
    }

    @Override
    public void move() {
        position.setLocation(position.x + speed.x, position.y + speed.y);
        collisionObject.transfer( position.x + collisionRelativeDistance.x, position.y + collisionRelativeDistance.y);
        collRect.setX((int)(((RectangleCollisionObject)collisionObject).getRectangle().getX()));
        collRect.setY((int)(((RectangleCollisionObject)collisionObject).getRectangle().getY()));
        updateImage();
    }

    @Override
    public void attack() {

    }

    @Override
//    public Node getImage() {
//        return imageManager.getImageView();
//    }
    public Node getImage() {
//        return new Group(collRect, imageManager.getImageView());
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
    public void disable() {

    }

    @Override
    protected Point getCollisionRelativeDistance() {
        return collisionRelativeDistance;
    }
}
