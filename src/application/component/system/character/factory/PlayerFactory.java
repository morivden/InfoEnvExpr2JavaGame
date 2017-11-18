package application.component.system.character.factory;

import application.component.objects.GameObject;
import application.component.objects.character.PlayableCharacter;
import application.component.objects.character.implement_character.TMPCharacter;
import application.component.system.character.controller.CharacterController;
import application.component.system.character.controller.Player;
// import com.sun.javafx.geom.Point2D;

import java.awt.Point;
import java.util.Optional;

/**
 * プレイヤーが操作するキャラクターを生成するクラス
 */
public class PlayerFactory extends CharacterFactory<Player> {
    private static PlayerFactory ourInstance = new PlayerFactory();

    private CharacterController createdCharacterController;   // 自身が作成したキャラクターコントローラのインスタンス

    private PlayerFactory() {
<<<<<<< HEAD
        super(GameObjectList.Hero, new Point2D());
=======
        super(FactoryList.Hero, new Point());
>>>>>>> master
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
    public Optional<CharacterController> getPlayerCharacter() {
        return Optional.ofNullable(createdCharacterController);
    }

    @Override
    public void updateAll() {
        createdCharacterController.update();

    }

    @Override
    public Player create() {
        GameObject tmpCharacter = gameObjectList.getInstance(createPosition);
        PlayableCharacter createP = null;
        if ( tmpCharacter instanceof PlayableCharacter ) {
            createP = (PlayableCharacter) tmpCharacter;
        } else {
            createP = new TMPCharacter(createPosition);
        }
        return new Player(createP);
    }

    @Override
    protected void register(Player cc) {
        createdCharacterController = cc;
    }
}
