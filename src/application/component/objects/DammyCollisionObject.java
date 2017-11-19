package application.component.objects;

public class DammyCollisionObject extends CollisionObject {
    @Override
    public void igniteEvents(CollisionObject collidedObj, GameObject gameObject, CollisionObject collidingObj) {

    }

    @Override
    public boolean isCollide(CollisionObject collisionObject) {
        return false;
    }

    @Override
    public void transfer(int x, int y) {

    }

    @Override
    public void move(int dx, int dy) {

    }
}
