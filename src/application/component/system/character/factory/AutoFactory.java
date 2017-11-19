package application.component.system.character.factory;

import application.component.objects.GameObject;
import application.component.objects.character.PlayableCharacter;
import application.component.system.GameManager;
import application.component.system.character.controller.CharacterController;
import application.component.system.character.controller.Enemy;
import javafx.application.Platform;
// import com.sun.javafx.geom.Point2D;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 自動操作のキャラクターを生成するクラス
 */
public class AutoFactory extends CharacterFactory<Enemy> {
    private static int MAX_NUMBER_OF_CREATE_CHARACTER = 5;  // 生成できるキャラクタノーの上限
    private static long COOL_TIME_OF_CREATING = 10000;       // キャラクター生成のクールタイム(ms)
    private List<CharacterController> createdCharacterController = new ArrayList<>();  // 自身が作成したキャラクターコントローラのインスタンス

    private long previousCreateTime = 0;  // 以前の生成時間

    public AutoFactory(GameObjectList gol,  Point createPosition) {
        super(gol, createPosition);
    }

    @Override
    public void updateAll() {
        for ( CharacterController cc : createdCharacterController ) {
            cc.update();  // 状態の更新
        }
    }

    @Override
    public Optional<Enemy> create() {
        long currentCreateTime = System.currentTimeMillis();  // 現在の生成時間

        // 生成可能判定
        if ( !(createdCharacterController.size() <= MAX_NUMBER_OF_CREATE_CHARACTER &&
                currentCreateTime - previousCreateTime > COOL_TIME_OF_CREATING &&
                !GameManager.isValid(createPosition)) ) {
            return Optional.empty();
        }

        GameObject newGameObject = gameObjectList.getInstance(new Point(createPosition.x, createPosition.y));

        // 生成オブジェクトがPlayableObjectか判定
        if ( !(newGameObject instanceof PlayableCharacter) ) {
            return Optional.empty();
        }

        Platform.runLater(() -> GameManager.addGameObject(newGameObject));  // ゲームオブジェクトの登録
        // コントローラへの譲渡
        Enemy enemy = new Enemy((PlayableCharacter) newGameObject);
        register(enemy);  // リストへの追加

        previousCreateTime = currentCreateTime;  // 前回の生成時間の更新

        return Optional.of(enemy);
    }

    @Override
    protected void register(Enemy cc) {
        createdCharacterController.add(cc);
    }
}
