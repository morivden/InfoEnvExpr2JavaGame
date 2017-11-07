package application.component.objects;

import java.util.List;

public abstract class CollisionObject {
    public static List<CollisionObject> collisionObjects;
    
    
    public CollisionObject() {
        
    }
    
    /**
     * オブジェクトごとの衝突チェック
     * @param gameObject
     */
    public static void checkCollisions(GameObject gameObject) {
        // 衝突してるかをチェック
        // チェックしたいCollisionObjectをcollisionObjectsからピックアップ
        for (CollisionObject pickupCollisionObject : collisionObjects) {
            // 全てのcollisionObjectsを見ていく
            for (CollisionObject collisionObject : collisionObjects) {
                // GameObject側からCollisionObjectの呼び出し
                CollisionObject collisionGameObject = gameObject.getCollisionObject();
                // 同じオブジェクトの場合はcontinue;
                if (pickupCollisionObject.equals(collisionGameObject)) {
                    continue;
                }
                // 衝突していたらigniteEventsを呼び出す
                if (pickupCollisionObject.isCollide(collisionGameObject)) {
                    pickupCollisionObject.igniteEvents(gameObject);
                }
            }
        }
        
        // 衝突してたらiginteEventsで発火
    }
    
    public abstract void igniteEvents(GameObject gameObject);
    
    public abstract boolean isCollide(CollisionObject collisionObject);/** {
        if (collisionObject.instanceOf(RectangleCollisionObject)) {
            // TODO 後でgetRectangleメソッドを実装
            // 型キャストしてgetRectangleメソッドを呼び出す
            // ((RectangleCollisionObject)collisionObject).getRectangle();
        }
        return true;
    }**/
    
    
    // 座標移動
    public abstract void transfer(double x, double y);
}