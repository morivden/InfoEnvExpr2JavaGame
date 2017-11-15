package application.component.objects.stage;

import java.awt.Rectangle;
import application.component.objects.*;
import application.component.objects.character.MovableObject;

public abstract class StageObject extends GameObject {    
    public StageObject() {
        StageCollision stageCollision = new StageCollision();
    }
    
    public static class StageCollision extends CollisionEvent {
        private double x, y, width, height;
        
        public StageCollision() {
            
        }
        
        /**
         * イベントの発火(衝突したときの処理)
         * @param gameObject
         */
        @Override
        public void ignite(CollisionObject collidedObj, GameObject gameObject, CollisionObject collidingObj) {           
            // gameObjectがMovableObjectのインスタンスかどうか
            if (gameObject instanceof MovableObject) {
                // MovableObjectにキャスト
                MovableObject movalbeObject = ((MovableObject)gameObject);
                
                // gameObjectからCollisionObjectを引っ張ってくる
                CollisionObject collisionObject = gameObject.getCollisionObject();
                // RectangleCollisionObjectにキャストし、Rectangleを引っ張ってくる
                Rectangle rect1 = ((RectangleCollisionObject)collisionObject).getRectangle();
                
                // 引っ張ってきたRectangleからx座標、y座標、幅、高さを引っ張ってくる
                x = rect1.getX();
                y = rect1.getY();
                width = rect1.getWidth();
                height = rect1.getHeight();
                
                // StageObject側のRectangleを引っ張ってくる
                
                // 引っ張ってきたRectangleからx座標、y座標、幅、高さを引っ張ってくる
                
                // 上からぶつかっているか
                // 下からぶつかっているか
                // 左からぶつかっているか
                // 右からぶつかっているか
            }
        }
    }
}