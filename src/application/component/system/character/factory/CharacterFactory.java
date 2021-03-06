package application.component.system.character.factory;

import application.component.system.character.controller.CharacterController;
// import com.sun.javafx.geom.Point2D;
import java.awt.Point;
import java.util.Optional;

/**
 * キャラクター生成クラス
 *
 * @param <T> 生成するキャラクターコントローラクラス
 */
public abstract class CharacterFactory<T extends CharacterController> {
    protected GameObjectList gameObjectList;  // ファクトリーリスト
    protected Point createPosition;   // 生成ポイント

    public CharacterFactory(GameObjectList fl, Point createPosition) {
        gameObjectList = fl;
        this.createPosition = createPosition;
    }

    /**
     * キャラクター生成メソッド
     *
     * @return  生成したキャラクターコントローラのインスタンス
     */
    public abstract Optional<T> create();

    /**
     * 保持しているコントローラクラスのインスタンス更新メソッド
     */
    public abstract void updateAll();

    /**
     * 保持しているコントローラクラスのキャラクタークラスのインスタンス
     */
    public abstract void checkLifeTile();

    /**
     * キャラクターコントローラの登録
     *
     * @param cc the cc
     */
    protected abstract void  register(T cc);
}
