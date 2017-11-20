package application.component.objects.stage.implement_stage;

import application.component.objects.stage.StageObject;
import application.component.system.GameEnvironment;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class TMPStageBlock extends StageObject {
    private Rectangle rect = new Rectangle(GameEnvironment.getBlockScale(), GameEnvironment.getBlockScale());

    public TMPStageBlock(Point pos) {
        super(pos);
        position = pos;
        rect.setFill(Color.AQUA);

        updateImg();
    }

    private void updateImg() {
        rect.setTranslateX(position.x);
        rect.setTranslateY(position.y);
    }

    @Override
    public Node getImage() {
        return rect;
    }
    
    public void updateImage() {
        
    }
}
