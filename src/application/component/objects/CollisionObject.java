package application.component.objects;

import java.util.List;

public abstract class CollisionObject {
    //public static List<CollisionObject> collisionObjects;
    
    public CollisionObject() {
        
    }
    
    /**
     * オブジェクトごとの衝突チェック
     * @param gameObject
     */
    public static void checkCollisions(List<CollisionObject> co, GameObject gameObject) {
        // 衝突してるかをチェック
        // チェックしたいCollisionObjectをcollisionObjectsからピックアップ
        for (CollisionObject pickupCollisionObject : co) {
            // GameObjectからCollisionObjectを取り出す
            CollisionObject collisionGameObject = gameObject.getCollisionObject();
            // 同じオブジェクトの場合はcontinue;
            if (pickupCollisionObject.equals(collisionGameObject)) {
                continue;
            }
            // 衝突していたらigniteEventsを呼び出す
            if (pickupCollisionObject.isCollide(collisionGameObject)) {
                pickupCollisionObject.igniteEvents(gameObject);
                collisionGameObject.igniteEvents(gameObject);
            }
        }
        
        // 衝突してたらiginteEventsで発火
    }
    
    // イベントの発火
    public abstract void igniteEvents(GameObject gameObject);
    
    // 衝突判定
    public abstract boolean isCollide(CollisionObject collisionObject);    
    
    // 座標移動
    public abstract void transfer(double x, double y);
}