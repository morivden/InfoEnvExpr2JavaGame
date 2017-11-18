package application.component.objects.stage.implement_stage;

import application.component.objects.ImageManager;
import application.component.objects.RectangleCollisionObject;
import application.component.objects.stage.StageObject;
import application.component.system.GameEnvironment;
import javafx.scene.Node;
import javafx.scene.image.Image;

import java.awt.*;

public class RectangleStageBlock extends StageObject {
    private static String WAIT_IMAGE = "/images/rect_stage.png";

    private Point collisionRelativeDistance;

    public RectangleStageBlock(Point pos) {
        super(pos);

        //===  イメージ関連
        javafx.scene.image.Image waitImage = new Image(WAIT_IMAGE, GameEnvironment.getBlockScale(), GameEnvironment.getBlockScale(), true, true);
        imageManager.addImage(ImageManager.ObjectStatus.WAIT, waitImage);
        imageManager.showImage(ImageManager.ObjectStatus.WAIT);

        //=== 衝突物体の相対距離算出
        collisionRelativeDistance = new Point(-(int)(waitImage.getWidth() / 2), -(int)(waitImage.getHeight() / 2));

        //=== 衝突物体関連
        RectangleCollisionObject rectCO = new RectangleCollisionObject(pos.x, pos.y,
                GameEnvironment.getBlockScale(), GameEnvironment.getBlockScale());
        rectCO.addEvent(new StageCollision());
        collisionObject = rectCO;

        updateImage();
    }

    private void updateImage() {
        int posX = (int)(position.x - imageManager.getImageView().getImage().getWidth()/2);
        int posY = (int)(position.y - imageManager.getImageView().getImage().getHeight()/2);

        imageManager.transfer(posX, posY);
    }

    @Override
    public Node getImage() {
        return imageManager.getImageView();
    }
}
