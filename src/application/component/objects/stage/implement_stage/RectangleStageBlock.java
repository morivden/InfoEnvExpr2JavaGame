package application.component.objects.stage.implement_stage;

import application.component.objects.RectangleCollisionObject;
import application.component.objects.stage.StageObject;
import application.component.system.GameEnvironment;
import javafx.scene.Node;

import java.awt.*;

public class RectangleStageBlock extends StageObject {

    public RectangleStageBlock(Point pos) {
        super(pos);
        collisionObject = new RectangleCollisionObject(pos.x, pos.y,
                GameEnvironment.getBlockScale(), GameEnvironment.getBlockScale());
    }

    @Override
    public Node getImage() {
        return null;
    }
}
