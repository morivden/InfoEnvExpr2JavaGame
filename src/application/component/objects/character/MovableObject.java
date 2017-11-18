package application.component.objects.character;

import java.awt.*;
import java.util.List;

public interface MovableObject {

    /**
     * リストに格納された全てのMovableObjectの移動
     *
     * @param movableObjects
     */
    static void moveMovableObjects(List<MovableObject> movableObjects) {
        movableObjects.stream().forEach(mo -> mo.move());
    }

    /**
     * スピード値の反映
     */
    void move();

    /**
     * スピード値の変更
     * @param xSpeed
     * @param ySpeed
     */
    default void setSpeed(int xSpeed, int ySpeed) {
        // 適正判定
        xSpeed = checkSpeed(xSpeed);
        ySpeed = checkSpeed(ySpeed);

        // 格納
        getSpeed().setLocation(xSpeed, ySpeed);
    }

    /**
     * スピードの適性を判定
     *
     * @param speed
     * @return
     */
    default int checkSpeed(int speed) {
        int maxSpeed = getMaxSpeed();
        if ( -maxSpeed > speed ) { speed = -maxSpeed; }
        else if ( maxSpeed < speed ) { speed = maxSpeed; }

        return speed;
    }

    /**
     * X方向のスピード値の取得
     *
     * @return
     */
    int getXSpeed();

    /**
     * Y方向のスピード値の取得
     *
     * @return
     */
    int getYSpeed();

    /**
     * 標準のスピードを取得
     *
     * @return
     */
    int getDefaultSpeed();

    /**
     * 最高スピードを取得
     *
     * @return
     */
    int getMaxSpeed();

    /**
     * スピードの取得
     *
     * @return
     */
    Point getSpeed();
}
