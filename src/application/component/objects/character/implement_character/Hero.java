package application.component.objects.character.implement_character;

import application.component.objects.ImageManager;
import application.component.objects.RectangleCollisionObject;
import application.component.objects.character.PlayableCharacter;
import application.component.system.GameEnvironment;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.awt.*;


public class Hero extends PlayableCharacter {
    private static String WAIT_IMAGE = "/images/player/wait.png";
    private static String LEFT_IMAGE = "/images/player/left.png";
    private static String RIGHT_IMAGE = "/images/player/right.png";
    private static String HEART_IMAGE = "/images/heart.png";
    private static String JUMP_IMAGE = "/images/player/jump.png";
    private static String DEAD_IMAGE = "/images/enemy/dead.png";
    public static int DEFAULT_SPEED = 10;
    public static int JUMP_SPEED = -14;
    public static int MAX_SPEED = 100;
    public static int DEFAULT_HP = 100;

    public static int HEART_DEFAULT_SIZE = 10;

    private Rectangle collRect;

    private Point collisionRelativeDistance;

    private Group playerImage;
    private ImageView heartView;  // 体力表示用のハート画像
    private static ColorAdjust screenLight = new ColorAdjust();  // ハート画像の色を変えるエフェクト

    public Hero(Point pos) {        
        super(pos);
        this.setOnGround(false);

        hp = DEFAULT_HP;

        //===  イメージ関連
        Image waitImage = new Image(WAIT_IMAGE, GameEnvironment.getBlockScale(), GameEnvironment.getBlockScale(), true, true);
        Image leftImage = new Image(LEFT_IMAGE, GameEnvironment.getBlockScale(), GameEnvironment.getBlockScale(), true, true);
        Image rightImage = new Image(RIGHT_IMAGE, GameEnvironment.getBlockScale(), GameEnvironment.getBlockScale(), true, true);
        Image jumpImage = new Image(JUMP_IMAGE, GameEnvironment.getBlockScale(), GameEnvironment.getBlockScale(), true, true);
        Image deadImage = new Image(DEAD_IMAGE, GameEnvironment.getBlockScale(), GameEnvironment.getBlockScale(), true, true);
        
        imageManager.addImage(ImageManager.ObjectStatus.WAIT, waitImage);
        imageManager.addImage(ImageManager.ObjectStatus.IMG_LEFT, leftImage);
        imageManager.addImage(ImageManager.ObjectStatus.IMG_RIGHT, rightImage);
        imageManager.addImage(ImageManager.ObjectStatus.JUMP, jumpImage);
        imageManager.addImage(ImageManager.ObjectStatus.DEAD, deadImage);
        
        imageManager.showImage(ImageManager.ObjectStatus.WAIT);

        // 体力関連
        Image heart = new Image(HEART_IMAGE, HEART_DEFAULT_SIZE, HEART_DEFAULT_SIZE, true, true);;

        heartView =  new ImageView(heart);
        playerImage = new Group(imageManager.getImageView(), heartView);
        heartView.setEffect(screenLight);

        //=== 衝突物体の相対距離算出
        collisionRelativeDistance = new Point(-(int)(waitImage.getWidth() / 2), -(int)(waitImage.getHeight() / 2));

        //=== 衝突物体関連
        RectangleCollisionObject rectCO = new RectangleCollisionObject(position.x + collisionRelativeDistance.x,
                position.y + collisionRelativeDistance.y, (int)waitImage.getWidth(), (int) waitImage.getHeight());
//        rectCO.addEvent((o, e, a) -> System.out.println("  ヒーロー衝突"));
        collisionObject = rectCO;

        moveImage();
    }

    @Override
    public void move() {
        position.setLocation(position.x + speed.x, position.y + speed.y);
        collisionObject.transfer( position.x + collisionRelativeDistance.x, position.y + collisionRelativeDistance.y);

        moveImage();
    }
    
    // ジャンプ
    public void jump() {
        // 接地している状態の時
        if (this.isOnGround() && this.getYSpeed() == GameEnvironment.getGravity()) {
            this.setSpeed(this.getXSpeed(), JUMP_SPEED);
            // 接地状態をfalseにする
            this.setOnGround(false);
        }
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
        return playerImage;
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
    
    // 進行方向ごとの画像の切り替え
    public void updateImage() {
        // スピードの方向で進行方向を判断
        // y方向
        if (speed.y < 0) {
            // ジャンプ
            imageManager.showImage(ImageManager.ObjectStatus.JUMP);
        }
        
        // x方向
        if (speed.x < 0) {
            // 左方向に移動
            imageManager.showImage(ImageManager.ObjectStatus.IMG_LEFT);
        } else if (speed.x > 0) {
            // 右方向に移動
            imageManager.showImage(ImageManager.ObjectStatus.IMG_RIGHT);
        } else {
            // 待機状態
            imageManager.showImage(ImageManager.ObjectStatus.WAIT);
        }
        
        // 死んだ状態
        if (this.hp == 0) {
            imageManager.showImage(ImageManager.ObjectStatus.DEAD);
        }
    }

    @Override
    protected void moveImage() {
        super.moveImage();

        heartView.setX(getPosition().x - HEART_DEFAULT_SIZE / 2);
        heartView.setY(getPosition().y - GameEnvironment.getBlockScale() / 2 - HEART_DEFAULT_SIZE );

        screenLight.setBrightness( getHp() / (double)DEFAULT_HP - 1);
    }
}
