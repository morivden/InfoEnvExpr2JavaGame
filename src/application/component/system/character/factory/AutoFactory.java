package application.component.system.character.factory;

import application.component.system.character.controller.CharacterController;
import application.component.system.character.controller.Enemy;
// import com.sun.javafx.geom.Point2D;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * 自動操作のキャラクターを生成するクラス
 */
public class AutoFactory extends CharacterFactory<Enemy> {

    private List<CharacterController> createdCharacterController = new ArrayList<>();  // 自身が作成したキャラクターコントローラのインスタンス

    public AutoFactory(FactoryList fl,  Point createPosition) {
        super(fl, createPosition);
    }

    @Override
    public void updateAll() {
        for ( CharacterController cc : createdCharacterController ) {
            cc.update();  // 状態の更新
        }
    }

    @Override
    public Enemy create() {
        return null;
    }

    @Override
    protected void register(Enemy cc) {
        createdCharacterController.add(cc);
    }
}
