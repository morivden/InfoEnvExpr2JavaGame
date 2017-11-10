package application.component.system.character.factory;

import application.component.system.character.controller.Enemy;

public class AutoFactory implements CharacterFactory<Enemy> {
//    private List<PlayableCharacter> createdCharacterList = new ArrayList<>();  // 自身が作成したキャラクターのインスタンス

    @Override
    public void updateAll() {

    }

    @Override
    public Enemy create() {
        return null;
    }
}
