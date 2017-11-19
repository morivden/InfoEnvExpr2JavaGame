package application.component.objects;

import javafx.scene.Node;
import java.awt.Point;
import java.util.Optional;

public abstract class GameObject {
    protected Point position;
    protected CollisionObject collisionObject;
    protected ImageManager imageManager = new ImageManager();
    protected Optional<Long> lifeTime = Optional.empty();  // オブジェクトの有効期間
    
    public GameObject(Point pos) {
        position = pos;
    }

    public abstract Node getImage();
    
    public Point getPosition() {
        return position;
    }

    public Optional<Long> getLifeTime() {
        return lifeTime;
    }

    public CollisionObject getCollisionObject() {
        return collisionObject;
    }
}
