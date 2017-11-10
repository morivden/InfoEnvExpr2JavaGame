package application.component.system.character.factory;

import application.component.system.character.controller.CharacterController;

public interface CharacterFactory<T extends CharacterController> {
    /**
     * 保持しているコントローラクラスのインスタンス更新メソッド
     */
    void updateAll();

    /**
     * キャラクター生成メソッド
     *
     * @return  生成したキャラクターコントローラのインスタンス
     */
    T create();
}
