package application.component.objects;

import java.awt.Rectangle;
import java.util.List;

public class RectangleCollisionObject extends CollisionObject {
    protected List<CollisionEvent> events;
    protected Rectangle rect1;
    
    /**
     * コンストラクタ
     * @param x, y, width, height
     */
    public RectangleCollisionObject(double x, double y, double width, double height) {
        rect1.setRect(x, y, width, height);
    }
    
    /**
     * イベントの発火
     * @param gameObject
     */
    public void igniteEvents(CollisionObject collidedObj, GameObject gameObject, CollisionObject collidingObj) {
        for (CollisionEvent event : events) {
            event.ignite(collidedObj, gameObject, collidingObj);
        }
    }
    
    /**
     * 衝突判定
     * @param collisionObject
     */
    public boolean isCollide(CollisionObject collisionObject) {
        // collisionObjectがRectangleCollisionObjectのインスタンスであるかどうか
        if (collisionObject instanceof RectangleCollisionObject) {
            // 型キャストしてgetRectangleメソッドを呼び出す
            Rectangle rect2 = ((RectangleCollisionObject)collisionObject).getRectangle();
            // 矩形が重なっているか判定
            if (rect1.intersects(rect2)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 指定した座標(矩形の左上指定)への移動
     * @param x, y
     */
    public void transfer(double x, double y) {
        rect1.setLocation((int)x, (int)y);
    }
    
    /**
     * イベントの追加
     * @param e
     */
    public void addEvent(CollisionEvent e) {
        events.add(e);
    }
    
    /**
     * Rectangleオブジェクトを返す
     * 
     */
    public Rectangle getRectangle() {
        return rect1;
    }
}