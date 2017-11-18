package application.component.objects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RectangleCollisionObject extends CollisionObject {
    protected List<CollisionEvent> events;
    protected Rectangle rect;

    /**
     * コンストラクタ
     * @param x, y, width, height
     */
    public RectangleCollisionObject(int x, int y, int width, int height) {
        rect = new Rectangle(x, y, width, height);
        events = new ArrayList<>();
        rect.setBounds(x, y, width, height);
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
            Rectangle collidingRect = ((RectangleCollisionObject)collisionObject).getRectangle();
            // 矩形が重なっているか判定
            if ( rect.intersects(collidingRect) ) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 指定した座標(矩形の左上指定)への移動
     * @param x, y
     */
    public void transfer(int x, int y) {
        rect.setLocation(x, y);
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
        return rect;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("(%d, %d, %d, %d)", rect.x, rect.y, rect.width, rect.height));

        return new String(sb);
    }
}
