package application.component.objects;

import javafx.scene.Node;
import java.awt.Point;

public abstract class GameObject {
    // protected Point2D position;
    protected Point position;
    protected CollisionObject collisionObject;
    protected ImageManager imageManager;
    
    public GameObject(Point pos) {
        position = pos;
    }

    public abstract Node getImage();
    
    public Point getPosition() {
        return position;
    }
    
    public CollisionObject getCollisionObject() {
        return collisionObject;
    }
}
