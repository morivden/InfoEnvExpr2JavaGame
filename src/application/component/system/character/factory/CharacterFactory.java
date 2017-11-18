package application.component.system.character.factory;

import application.component.objects.GameObject;
import application.component.objects.character.PlayableCharacter;
import application.component.system.character.controller.CharacterController;
// import com.sun.javafx.geom.Point2D;
import java.awt.Point;

/**
 * キャラクター生成クラス
 *
 * @param <T> 生成するキャラクターコントローラクラス
 */
public abstract class CharacterFactory<T extends CharacterController> {
    protected Point createPosition;   // 生成ポイント
    protected FactoryList factoryList;  // ファクトリーリスト

    public CharacterFactory(FactoryList fl, Point createPosition) {

        factoryList = fl;
        this.createPosition = createPosition;
    }

    /**
     * キャラクター生成メソッド
     *
     * @return  生成したキャラクターコントローラのインスタンス
     */
    public abstract T create();

    /**
     * 保持しているコントローラクラスのインスタンス更新メソッド
     */
    public abstract void updateAll();

    /**
     * キャラクターコントローラの登録
     *
     * @param cc the cc
     */
    protected abstract void  register(T cc);
}
