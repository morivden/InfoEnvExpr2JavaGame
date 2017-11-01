package application.component.system;

public class GameManager {
    private static GameManager ourInstance = new GameManager();

    public static GameManager getInstance() {
        return ourInstance;
    }

    private GameManager() {


    }
}
