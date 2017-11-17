package application.component.objects.stage.implement_stage;

import application.component.objects.stage.StageObject;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TMPStageBlock extends StageObject {
    private Rectangle rect = new Rectangle(10, 10);

    public TMPStageBlock(Point2D pos) {
        position = pos;
        rect.setFill(Color.AQUA);

        updateImg();
    }

    private void updateImg() {
        rect.setTranslateX(position.getX());
        rect.setTranslateY(position.getY());
    }
}
