package application.component.objects.stage.implement_stage;

import application.component.objects.ImageManager;
import application.component.objects.RectangleCollisionObject;
import application.component.objects.stage.StageObject;
import application.component.system.GameEnvironment;
import application.component.system.character.factory.PlayerFactory;
import javafx.scene.Node;
import javafx.scene.image.Image;

import java.awt.*;

public class GoalBlock extends StageObject {
    private static String WAIT_IMAGE = "/images/goal.png";

    public GoalBlock(Point pos) {
        super(pos);

        //=== イメージ関連
        Image waitImage = new Image(WAIT_IMAGE, GameEnvironment.getBlockScale(), GameEnvironment.getBlockScale(), true, true);
        imageManager.addImage(ImageManager.ObjectStatus.WAIT, waitImage);
        imageManager.showImage(ImageManager.ObjectStatus.WAIT);

        //=== 衝突物体関連
        RectangleCollisionObject rectCO = new RectangleCollisionObject(position.x - (int)waitImage.getHeight(),
                position.y - (int)waitImage.getHeight() / 2, (int)waitImage.getWidth(), (int) waitImage.getHeight());
        collisionObject = rectCO;
        rectCO.addEvent((ed, go, ing) -> {
            PlayerFactory.getPlayerCharacterController().ifPresent(player -> {
                if ( go == player.getCharacter() ) {
                    // TODO　GameManagerに対して、ゲームクリアリクエストを送る処理を追加する
                    System.out.println("ゴール");
                }
            });
        });

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
