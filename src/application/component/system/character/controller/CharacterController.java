package application.component.system.character.controller;

import application.component.objects.character.PlayableCharacter;

public abstract class CharacterController {
    /**
     * キャラクタークラスのインスタンス更新メソッド
     */
    public void update() {
        //== 更新判定
        if ( !checkUpdateValid() ) {
            notUpdate();
            return;
        }

        //== スピードに関するメソッド
        updateSpeed();

        //== 寿命に関するメソッド
        updateLifeTime();
    }

    /**
     * 更新を行うかどうかの判定メソッド
     *
     * @return
     */
    protected abstract boolean checkUpdateValid();

    /**
     * 更新を行わない祭に行われる事後メソッド
     */

    protected abstract void notUpdate();

    /**
     * 移動に関する更新メソッド
     */
    protected abstract void updateSpeed();

    /**
     * 寿命に関するメソッド
     */
    protected abstract void updateLifeTime();

    /**
     * キャラクタークラス取得メソッド
     * @return
     */
    public abstract PlayableCharacter getCharacter();

}
