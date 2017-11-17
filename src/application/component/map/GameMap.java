package application.component.map;

import application.component.objects.GameObject;
import application.component.objects.character.MovableObject;
import application.component.objects.character.OffensiveObject;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    // TODO List<GameObject> 変数の宣言
    List<GameObject> gameObjects = new ArrayList<>();
    List<MovableObject> movableObjects = new ArrayList<>();
    List<OffensiveObject> offensiveObjects = new ArrayList<>();

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
