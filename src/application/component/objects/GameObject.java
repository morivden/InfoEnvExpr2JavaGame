package application.component.objects;

import javafx.scene.Node;
import java.awt.Point;

public abstract class GameObject {
    // protected Point2D position;
    protected Point position;
    protected CollisionObject collisionObject;
    protected ImageManager imageManager;
    
    public GameObject() {
        
    }

    public abstract Node getImg();
    
    public Point getPosition() {
        return position;
    }
    
    public CollisionObject getCollisionObject() {
        return collisionObject;
    }
}
