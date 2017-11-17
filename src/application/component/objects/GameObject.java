package application.component.objects;

import com.sun.javafx.geom.Point2D;
import javafx.scene.Node;

public abstract class GameObject {
    protected Point2D position;
    protected CollisionObject collisionObject;
    protected ImageManager imageManager;
    
    public GameObject() {
        
    }

    public abstract Node getImg();
    
    public Point2D getPosition() {
        return position;
    }
    
    public CollisionObject getCollisionObject() {
        return collisionObject;
    }
}
