package application.component.system;

import application.component.map.GameMap;
import application.component.objects.GameObject;
import application.component.objects.character.implement_character.TMPCharacter;
import application.controller.GameController;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * 描画パネル管理クラス
 */
public class DrawPanelManager {
    private GameMap gameMap;  // マップ
    private Pane drawPane;    // 描画パネル
    private GameEnvironment gameEnvironment;  // 環境値

    public DrawPanelManager(GameMap gameMap, Pane drawPane) {
        this.gameMap = gameMap;
        this.drawPane = drawPane;

        inputMap(gameMap);
    }

    /**
     * マップの反映
     *
     * @param gm the gm
     */
    private void inputMap(GameMap gm) {
        drawPane.getChildren().removeAll();  // 既存要素の削除
        drawPane.setPrefWidth(gm.getMapWidth());
        drawPane.setPrefHeight(gm.getMapHeight());
        // TODO GameObject実装後、実装
        List<GameObject> gameObjects = gm.getGameObjects();
        gameObjects.stream().forEach(go -> { drawPane.getChildren().add(go.getImg()); });
    }

    /**
     * オブジェクトの反映
     */
    public void inputGameObject(GameObject gameObject) {
        // TODO ImageManager実装後に、追加処理の追加
//        drawPane.getChildren().add();
    }

    // TODO 一時メソッド、あとで消す
    public void inputTMP(TMPCharacter tp) {
        gameMap.addGameObject(tp);
        drawPane.getChildren().add(tp.getImg());
        drawPane.getChildren().add(tp.getRange());
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    /**
     * パネル(の左上端座標)を親ノードのローカル座標上の指定の座標に移動
     * また、移動時には、パネルが画面全てを覆うように補正を行う。
     *
     * @param x x座標
     * @param y y座標
     */
    public void transfer(int x, int y) {
        int maxX = 0, maxY = 0;  // 上限
        int minX = (int)(GameController.getSceneWidth() - drawPane.getWidth());    // 下限
        int minY = (int)(GameController.getSceneHeight() - drawPane.getHeight());  // 下限

        // 補正
        if ( x < minX ) { x = minX; } else if ( x > maxX ) { x = maxX; }
        if ( y < minY ) { y = minY; } else if ( y > maxY ) { y = maxY; }

        // 格納
        drawPane.setLayoutX(x);
        drawPane.setLayoutY(y);;
    }
}
