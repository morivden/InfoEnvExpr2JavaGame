package application.component.system.character.factory;

import application.component.system.character.controller.Enemy;

public class AutoFactory implements CharacterFactory<Enemy> {
    @Override
    public void updateAll() {

    }

    @Override
    public Enemy create() {
        return null;
    }
}
