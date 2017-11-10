package application.component.system.character.factory;

import application.component.system.character.controller.Player;

public class PlayerFactory implements CharacterFactory<Player> {
    private static PlayerFactory ourInstance = new PlayerFactory();

    public static PlayerFactory getInstance() {
        return ourInstance;
    }

    private PlayerFactory() {
    }

    @Override
    public void updateAll() {

    }

    @Override
    public Player create() {
        return null;
    }
}
