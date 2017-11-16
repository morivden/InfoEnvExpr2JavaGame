package application.component.objects.stage;

import java.awt.Rectangle;
import application.component.objects.*;
import application.component.objects.character.MovableObject;

public abstract class StageObject extends GameObject {    
    public StageObject() {
        StageCollision stageCollision = new StageCollision();
    }
    
    public static class StageCollision extends CollisionEvent {
        // StageObject側の座標
        private double x1, y1, width1, height1;
        // MovableObject側の座標
        private double x2, y2, width2, height2;
        
        public StageCollision() {
            
        }
        
        /**
         * イベントの発火(衝突したときの処理)
         * @param collidedObje, gameObject, collidingOjb
         */
        @Override
        public void ignite(CollisionObject collidedObj, GameObject gameObject, CollisionObject collidingObj) {           
            // gameObjectがMovableObjectのインスタンスかどうか
            if (gameObject instanceof MovableObject) {
                // MovableObjectにキャスト
                MovableObject movalbeObject = ((MovableObject)gameObject);
                
                // gameObjectからCollisionObjectを引っ張ってくる
                //CollisionObject collisionObject = gameObject.getCollisionObject();
                // RectangleCollisionObjectにキャストし、Rectangleを引っ張ってくる
                Rectangle rect1 = ((RectangleCollisionObject)collidingObj).getRectangle();
                
                // 引っ張ってきたRectangleからx座標、y座標、幅、高さを引っ張ってくる
                x1 = rect1.getX();
                y1 = rect1.getY();
                width1 = rect1.getWidth();
                height1 = rect1.getHeight();
                
                // StageObject側のRectangleを引っ張ってくる
                Rectangle rect2 = ((RectangleCollisionObject)collidedObj).getRectangle();
                // 引っ張ってきたRectangleからx座標、y座標、幅、高さを引っ張ってくる
                x2 = rect2.getX();
                y2 = rect2.getY();
                width2 = rect2.getWidth();
                height2 = rect2.getHeight();
                
                // どの方向からぶつかっているか判定
                // 恐らくこのままだと重なり方によっては埋まる可能性がありそうなので
                // ぶつかってくる側の進行方向(vxとかvy)を判定の内容に使いたい
                if ((y1 + height1) > y2) {
                    // 上からぶつかっている場合
                    rect1.translate((int)x1, (int)(y2 - height1));
                } else if (y1 < (y2 + height2)) {
                    // 下からぶつかっている場合
                    rect1.translate((int)x1, (int)(y2 + height2));
                } else if ((x1 + width1) > x2) {
                    // 左からぶつかっている場合
                    rect1.translate((int)(x2 - width1), (int)y1);
                } else if (x1 < (x2 + width2)) {
                    // 右からぶつかっている場合
                    rect1.translate((int)(x2 + width2), (int)y1);
                }
            }
        }
    }
}