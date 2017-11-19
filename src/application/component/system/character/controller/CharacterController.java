package application.component.system.character.controller;

import application.component.objects.character.PlayableCharacter;

public abstract class CharacterController {
    /**
     * キャラクタークラスのインスタンス更新メソッド
     */
    public abstract void update();

    /**
     * キャラクタークラス取得メソッド
     * @return
     */
    public abstract PlayableCharacter getCharacter();
}
