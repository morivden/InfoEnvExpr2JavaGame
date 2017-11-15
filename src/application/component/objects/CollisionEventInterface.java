package application.component.objects;

public interface CollisionEventInterface {
    // イベントの発火
    void ignite(CollisionObject collidedObj, GameObject gameObject, CollisionObject collidingObj);
}