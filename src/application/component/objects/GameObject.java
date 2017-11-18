package application.component.objects;

// import com.sun.javafx.geom.Point2D;
import java.awt.Point;

public abstract class GameObject {
    // protected Point2D position;
    protected Point position;
    protected CollisionObject collisionObject;
    protected ImageManager imageManager;
    
    public GameObject() {
        
    }
    
    public Point getPosition() {
        return position;
    }
    
    public CollisionObject getCollisionObject() {
        return collisionObject;
    }
}