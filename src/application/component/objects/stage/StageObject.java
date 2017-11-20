package application.component.objects.stage;

import application.component.objects.CollisionEvent;
import application.component.objects.CollisionObject;
import application.component.objects.GameObject;
import application.component.objects.RectangleCollisionObject;
import application.component.objects.character.MovableObject;
import application.component.objects.character.PlayableCharacter;
import application.component.system.GameEnvironment;

import java.awt.*;

public abstract class StageObject extends GameObject {
    public StageObject(Point pos) {
        super(pos);
    }

    public static class StageCollision implements CollisionEvent {
        // StageObject側の座標
        private int x1, y1, width1, height1;
        // MovableObject側の座標
        private int x2, y2, width2, height2;
        // TMPCharacterのスピード
        private int xSpeed, ySpeed;
        
        public StageCollision() {
            
        }
        
        /**
         * イベントの発火(衝突したときの処理)
         * @param collidedObj, gameObject, collidingOjb
         */
        static final double ONE_ANGLE_SECTION = 360 / 8.0;
        @Override
        public void ignite(CollisionObject collidedObj, GameObject gameObject, CollisionObject collidingObj) {
            // gameObjectがMovableObjectのインスタンスかどうか
            if (gameObject instanceof MovableObject) {
                // MovableObjectにキャスト
                // MovableObject movableObject = ((MovableObject)gameObject);
                
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
                PlayableCharacter tmpCharacter = (PlayableCharacter)gameObject;

                // スピードを引っ張ってくる
                xSpeed = tmpCharacter.getXSpeed();
                ySpeed = tmpCharacter.getYSpeed();

                // 2点の間の角度
                double centerX1 = x1 + width1 / 2.0;
                double centerX2 = x2 + width2 / 2.0;
                double centerY1 = y1 + height1 / 2.0;
                double centerY2 = y2 + height2 / 2.0;

                double radian = Math.atan2(centerY2 - centerY1, centerX2 - centerX1);
                double degree = Math.toDegrees(radian) + 180;

                // y方向の当たり判定
                if ( ySpeed > 0 && ONE_ANGLE_SECTION * 5 <= degree && degree <= ONE_ANGLE_SECTION * 7 ) {
                    // 上からぶつかっている場合
//                    System.out.println("上から衝突");
                    tmpCharacter.setSpeed(tmpCharacter.getXSpeed(), ySpeed - (height1 - (y2 - y1)));
                    tmpCharacter.onGround = true;
                } else if ( ySpeed < 0 && ONE_ANGLE_SECTION <= degree && degree <= ONE_ANGLE_SECTION * 3 ) {
                    // 下からぶつかっている場合
//                    System.out.println("下から衝突");
                    tmpCharacter.setSpeed(tmpCharacter.getXSpeed(), ySpeed + (height2 - (y1 - y2)));
                }
                
                // x_speedとy_speedをもとにどの方向からぶつかっているか判定
                // x方向の当たり判定
                if ( xSpeed > 0 && ONE_ANGLE_SECTION * 3 <= degree && degree <= ONE_ANGLE_SECTION * 5 ) {
                    // 左からぶつかっている場合
//                    System.out.println("左から衝突");
                    tmpCharacter.setSpeed(xSpeed - (width1 - (x2 - x1)), ySpeed);
                } else if ( xSpeed < 0 && (ONE_ANGLE_SECTION * 7 <= degree || degree <= ONE_ANGLE_SECTION )) {
                    // 右からぶつかっている場合
//                    System.out.println("右から衝突");
                    tmpCharacter.setSpeed(xSpeed + (width2 - (x1 - x2)), ySpeed);
                }
            }
        }
    }
}
