package application.component.map;

import application.component.objects.GameObject;
import application.component.objects.character.MovableObject;
import application.component.objects.character.OffensiveObject;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private List<GameObject> gameObjects = new ArrayList<>();
    private List<MovableObject> movableObjects = new ArrayList<>();
    private List<OffensiveObject> offensiveObjects = new ArrayList<>();

    private int mapWidth;
    private int mapHeight;

    public GameMap(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public List<MovableObject> getMovableObjects() {
        return movableObjects;
    }

    public List<OffensiveObject> getOffensiveObjects() {
        return offensiveObjects;
    }

    /**
     * ゲームオブジェクトの登録
     *
     * @param go the go
     */
    public void addGameObject(GameObject go) {
        gameObjects.add(go);
        if ( go instanceof MovableObject ) { movableObjects.add((MovableObject) go); }
        if ( go instanceof OffensiveObject ) { offensiveObjects.add((OffensiveObject) go); }
    }

    /**
     * ゲームオブジェクトの削除
     *
     * @param go the go
     */
    public void deleteGameObject(GameObject go) {
        gameObjects.remove(go);
        if ( go instanceof MovableObject ) { movableObjects.remove((MovableObject) go); }
        if ( go instanceof OffensiveObject ) { offensiveObjects.remove((OffensiveObject) go); }
    }
}
