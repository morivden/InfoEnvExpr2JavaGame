package application.component.system.character.factory;

import application.component.objects.character.PlayableCharacter;
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
        super(FactoryList.Hero, new Point());
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
        return new Player(factoryList.getInstance(createPosition));
    }

    @Override
    protected void register(Player cc) {
        createdCharacterController = cc;
    }
}
