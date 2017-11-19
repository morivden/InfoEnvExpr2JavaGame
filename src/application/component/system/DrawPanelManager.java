package application.component.system;

import application.component.map.GameMap;
import application.component.objects.CollisionObject;
import application.component.objects.GameObject;
import application.component.objects.RectangleCollisionObject;
import application.component.objects.character.implement_character.TMPCharacter;
import application.component.system.character.factory.CharacterFactory;
import application.controller.GameController;
import application.controller.GameControllerMain;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.List;

/**
 * 描画パネル管理クラス
 */
public class DrawPanelManager {
    public static int RANGE_MARGIN = 100;  // 有効範囲の外側への余白

    private GameMap gameMap;  // マップ
    private Pane drawPane;    // 描画パネル
    private GameEnvironment gameEnvironment;     // 環境値
    private List<CharacterFactory> factoryList;  // ファクトリークラスの一覧
    private Rectangle rangeOfActivities;         // オブジェクトの有効範囲

    public DrawPanelManager(GameMap gameMap, List<CharacterFactory> factoryList, Pane drawPane) {
        this.gameMap = gameMap;
        this.factoryList = factoryList;
        this.drawPane = drawPane;

        // 有効範囲の設定
        rangeOfActivities = new Rectangle(0, 0,
                (int)drawPane.getWidth() + RANGE_MARGIN * 2, (int)drawPane.getHeight() + RANGE_MARGIN * 2);

        updateRangeOfActivities(); // 有効範囲の更新
        inputMap(gameMap);         // オブジェクトの登録
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
        gameObjects.stream().forEach(go -> drawPane.getChildren().add(go.getImage()));
    }

    /**
     * オブジェクトの反映
     */
    public void inputGameObject(GameObject gameObject) {
        gameMap.addGameObject(gameObject);
        drawPane.getChildren().add(gameObject.getImage());
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
        drawPane.setTranslateX(x);
        drawPane.setTranslateY(y);

        // 有効範囲の更新
        updateRangeOfActivities();
    }

    public void transfer(Point pos) {
        transfer(pos.x, pos.y);
    }

    /**
     * 指定のゲームオブジェクトが画面上に写っているか判定
     *
     * @param go the go
     * @return the boolean
     */
    public boolean checkBeingShown(GameObject go) {
        CollisionObject co = go.getCollisionObject();

        // 矩形衝突物体のとき、判定
        if ( co instanceof RectangleCollisionObject ) {
            return ((RectangleCollisionObject) co).getRectangle().intersects(rangeOfActivities);
        }

        return false;
    }

    public boolean checkBeingShown(Point pos) {
        return rangeOfActivities.contains(pos);
    }

    /**
     * オブジェクトの有効範囲の更新
     */
    private void updateRangeOfActivities() {
        // 座標の算出
        int rangePosX = (int)drawPane.getLayoutX() - RANGE_MARGIN;
        int rangePosY = (int)drawPane.getLayoutY() - RANGE_MARGIN;

        rangeOfActivities.setLocation(rangePosX, rangePosY);
    }

    /**
     * ファクトリーリストの取得
     *
     * @return
     */
    public List<CharacterFactory> getFactoryList() {
        return factoryList;
    }

    /**
     * ファクトリーの追加
     *
     * @param cf
     */
    public void addFactory(CharacterFactory cf) {
        factoryList.add(cf);
    }
}
