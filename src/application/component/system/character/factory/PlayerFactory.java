package application.component.system.character.factory;

import application.component.objects.GameObject;
import application.component.objects.character.PlayableCharacter;
import application.component.objects.character.implement_character.TMPCharacter;
import application.component.system.GameManager;
import application.component.system.character.controller.CharacterController;
import application.component.system.character.controller.Player;
import javafx.application.Platform;
// import com.sun.javafx.geom.Point2D;

import java.awt.Point;
import java.util.Optional;

/**
 * プレイヤーが操作するキャラクターを生成するクラス
 */
public class PlayerFactory extends CharacterFactory<Player> {
    private static PlayerFactory ourInstance = new PlayerFactory();

    private Player createdCharacterController;   // 自身が作成したキャラクターコントローラのインスタンス

    private PlayerFactory() {
        super(GameObjectList.Hero, new Point());
    }

    /**
     * インスタンスの取得
     *
     * @param createPosition 生成位置
     * @return
     */
    public static PlayerFactory getInstance(Point createPosition) {
        ourInstance.setCreatePosition(createPosition);  // 位置の更新
        return ourInstance;
    }

    public static Point getCreatePosition() {
        return ourInstance.createPosition;
    }

    /**
     * 生成位置の格納
     *
     * @param createPosition
     */
    private static void setCreatePosition(Point createPosition) {
        ourInstance.createPosition = createPosition;
    }


    /**
     * 保持するキャラクターコントローラを取得
     *
     * @return プレイヤーが操作するキャラクターのコントローラ
     */
    public static Optional<Player> getPlayerCharacterController() {
        return Optional.ofNullable(ourInstance.createdCharacterController);
    }

    @Override
    public void updateAll() {
        if ( createdCharacterController != null ) {
            createdCharacterController.update();
        }
    }

    @Override
    public Optional<Player> create() {
        if ( createdCharacterController != null ) {
            return Optional.empty();
        }

        GameObject newGameObject = gameObjectList.getInstance(new Point(createPosition.x, createPosition.y));

        if ( !(newGameObject instanceof PlayableCharacter) ) {
            return Optional.empty();
        }

        PlayableCharacter createP = (PlayableCharacter) newGameObject;

        Platform.runLater(() -> GameManager.addGameObject(createP));  // ゲームオブジェクトの登録
        Player player = new Player(createP);
        register(player);  // コントローラの登録
        GameManager.registerPlayer(player);  // ゲームマネージャへの登録

        return Optional.of(player);
    }

    @Override
    public void checkLifeTile() {
        PlayableCharacter character = createdCharacterController.getCharacter();
        if ( character.getLifeTime().isPresent() &&
                System.currentTimeMillis() >  character.getLifeTime().get() ) {
            Platform.runLater(() -> GameManager.removeGameObject(character));  // 削除依頼
            createdCharacterController = null;  // 除去
        }
    }

    @Override
    protected void register(Player cc) {
        createdCharacterController = cc;
    }
}
