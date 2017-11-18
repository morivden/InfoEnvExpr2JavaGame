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
     * @param x_speed
     * @param y_speed
     */
    default void setSpeed(int x_speed, int y_speed) {
        // 適正判定
        x_speed = checkSpeed(x_speed);
        y_speed = checkSpeed(y_speed);

        // 格納
        getSpeed().setLocation(x_speed, y_speed);
    }

    /**
     * スピードの適性を判定
     *
     * @param speed
     * @return
     */
    default int checkSpeed(int speed) {
        int defaultSpeed = getDefaultSpeed();
        if ( -defaultSpeed > speed ) { speed = -defaultSpeed; }
        else if ( defaultSpeed < speed ) { speed = defaultSpeed; }

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
     * スピードの取得
     *
     * @return
     */
    Point getSpeed();
}
