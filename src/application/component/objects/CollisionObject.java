package application.component.objects;

import java.util.List;

public abstract class CollisionObject {

    // TODO 抽象クラスかインターフェースか要検討
    /**
     * オブジェクトごとの衝突チェック
     * @param gameObject
     */
    public static void checkCollisions(List<GameObject> go, GameObject gameObject) {
     // GameObjectからCollisionObjectを取り出す
        CollisionObject collisionGameObject = gameObject.getCollisionObject();
        // チェックしたいGameObjectをList<GameObject> goからピックアップ
        for (GameObject pickupGameObject : go) {
            // 同じGameObjectならcontinue
            if (pickupGameObject == gameObject) {
                continue;
            }
            // GameObjectからCollisionObjectを引っ張ってくる
            CollisionObject pickupCollisionObject = pickupGameObject.getCollisionObject();
            // 衝突していたらigniteEventsを呼び出す
            if (pickupCollisionObject.isCollide(collisionGameObject)) {
                pickupCollisionObject.igniteEvents(pickupCollisionObject, gameObject, collisionGameObject);
                collisionGameObject.igniteEvents(collisionGameObject, gameObject, pickupCollisionObject);
            }
        }
    }
    
    // イベントの発火
    public abstract void igniteEvents(CollisionObject collidedObj, GameObject gameObject, CollisionObject collidingObj);
    
    // 衝突判定
    public abstract boolean isCollide(CollisionObject collisionObject);    
    
    // 座標移動
    public abstract void transfer(double x, double y);
}