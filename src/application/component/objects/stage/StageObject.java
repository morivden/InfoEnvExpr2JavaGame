package application.component.objects.stage;

import java.awt.Rectangle;
import application.component.objects.*;
import application.component.objects.character.MovableObject;
import application.component.objects.character.implement_character.TMPCharacter;

public abstract class StageObject extends GameObject {    
    public StageObject() {
        StageCollision stageCollision = new StageCollision();
    }
    
    public static class StageCollision extends CollisionEvent {
        // StageObject側の座標
        private int x1, y1, width1, height1;
        // MovableObject側の座標
        private int x2, y2, width2, height2;
        // TMPCharacterのスピード
        private int x_speed, y_speed;
        
        public StageCollision() {
            
        }
        
        /**
         * イベントの発火(衝突したときの処理)
         * @param collidedObj, gameObject, collidingOjb
         */
        @Override
        public void ignite(CollisionObject collidedObj, GameObject gameObject, CollisionObject collidingObj) {           
            // gameObjectがMovableObjectのインスタンスかどうか
            if (gameObject instanceof MovableObject) {
                // MovableObjectにキャスト
                // MovableObject movalbeObject = ((MovableObject)gameObject);
                
                // gameObjectからCollisionObjectを引っ張ってくる
                //CollisionObject collisionObject = gameObject.getCollisionObject();
                // RectangleCollisionObjectにキャストし、Rectangleを引っ張ってくる
                Rectangle rect1 = ((RectangleCollisionObject)collidingObj).getRectangle();
                
                // 引っ張ってきたRectangleからx座標、y座標、幅、高さを引っ張ってくる
                x1 = (int)rect1.getX();
                y1 = (int)rect1.getY();
                width1 = (int)rect1.getWidth();
                height1 = (int)rect1.getHeight();
                
                // StageObject側のRectangleを引っ張ってくる
                Rectangle rect2 = ((RectangleCollisionObject)collidedObj).getRectangle();
                // 引っ張ってきたRectangleからx座標、y座標、幅、高さを引っ張ってくる
                x2 = (int)rect2.getX();
                y2 = (int)rect2.getY();
                width2 = (int)rect2.getWidth();
                height2 = (int)rect2.getHeight();
                
                // TMPCharacterにキャスト
                TMPCharacter tmpCharacter = (TMPCharacter)gameObject;
                // スピードを引っ張ってくる
                x_speed = tmpCharacter.getX_speed();
                y_speed = tmpCharacter.getY_speed();
                
                // x_speedとy_speedをもとにどの方向からぶつかっているか判定
                // x方向の当たり判定
                if (x_speed > 0) {
                    // 左からぶつかっている場合
                    System.out.println("左から衝突");
                    rect1.translate(x2 - width1, y1);
                } else if (x_speed < 0) {
                    // 右からぶつかっている場合
                    System.out.println("右から衝突");
                    rect1.translate(x2 + width2, y1);
                }
                
                // y方向の当たり判定                
                if (y_speed > 0) {
                    // 上からぶつかっている場合
                    System.out.println("上から衝突");
                    rect1.translate(x1, y2 - height1);
                } else if (y_speed > 0) {
                    // 下からぶつかっている場合
                    System.out.println("下から衝突");
                    rect1.translate(x1, y2 + height2);
                }
            }
        }
    }
}
