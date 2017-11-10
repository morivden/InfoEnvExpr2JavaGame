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
    public static void checkCollisions(List<GameObject> go, GameObject gameObject) {
     // GameObjectからCollisionObjectを取り出す
        CollisionObject collisionGameObject = gameObject.getCollisionObject();
        // 衝突してるかをチェック
        // チェックしたいCollisionObjectをcollisionObjectsからピックアップ
        for (GameObject pickupGameObject : go) {
            // 同じGameObjectならcontinue
            if (pickupGameObject == gameObject) {
                continue;
            }
            // GameObjectからCollisionObjectを引っ張ってくる
            CollisionObject pickupCollisionObject = pickupGameObject.getCollisionObject();
            // 衝突していたらigniteEventsを呼び出す
            if (pickupCollisionObject.isCollide(collisionGameObject)) {
                pickupCollisionObject.igniteEvents(gameObject);
                collisionGameObject.igniteEvents(pickupGameObject);
            }
        }
    }
    
    // イベントの発火
    public abstract void igniteEvents(GameObject gameObject);
    
    // 衝突判定
    public abstract boolean isCollide(CollisionObject collisionObject);    
    
    // 座標移動
    public abstract void transfer(double x, double y);
}