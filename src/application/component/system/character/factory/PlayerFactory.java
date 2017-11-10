package application.component.system.character.factory;

public class PlayerFactory {
    private static PlayerFactory ourInstance = new PlayerFactory();

    public static PlayerFactory getInstance() {
        return ourInstance;
    }

    private PlayerFactory() {
    }
}
