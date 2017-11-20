package application.component.system;

import application.Main;
import application.component.map.GameMap;
import application.component.objects.CollisionObject;
import application.component.objects.GameObject;
import application.component.objects.RectangleCollisionObject;
import application.component.system.character.factory.CharacterFactory;
import application.controller.GameController;
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
    private List<CharacterFactory> factoryList;  // ファクトリークラスの一覧
    private Rectangle rangeOfActivities;         // オブジェクトの有効範囲

    public DrawPanelManager(GameMap gameMap, List<CharacterFactory> factoryList, Pane drawPane) {
        this.gameMap = gameMap;
        this.factoryList = factoryList;
        this.drawPane = drawPane;

        inputMap(gameMap);         // オブジェクトの登録
    }

    /**
     * マップの反映
     *
     * @param gm the gm
     */
    private void inputMap(GameMap gm) {
        drawPane.getChildren().clear();  // 既存要素の削除
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

    /**
     * オブジェクトの排除
     *
     * @param gameObject
     */
    public void removeGameObject(GameObject gameObject) {
        gameMap.deleteGameObject(gameObject);
        drawPane.getChildren().remove(gameObject.getImage());
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    /**
     * パネル(の左上端座標)を親ノードのローカル座標上の指定の座標に移動
     * また、移動時には、パネルが画面全てを覆うように補正を行う。
     *
     * @param x          x座標
     * @param y          y座標
     */
    private void transfer(int x, int y) {
        // ルートのパネルの座標を基準とした
        int maxX = 0, maxY = 0;  // 上限
        int minX = (int)(GameController.getSceneWidth() - drawPane.getWidth());    // 下限
        int minY = (int)(GameController.getSceneHeight() - drawPane.getHeight());  // 下限

        // 補正
        if ( x < minX ) { x = minX; } else if ( x > maxX ) { x = maxX; }
        if ( y < minY ) { y = minY; } else if ( y > maxY ) { y = maxY; }

        // 格納
        drawPane.setLayoutX(x);
        drawPane.setLayoutY(y);
    }

    private void transfer(Point pos) {
        transfer(pos.x, pos.y);
    }

    /**
     * 基準点を中心に取るように描画パネルを移動
     *
     * @param pos the pos
     */
    public void focusPoint(Point pos) {
        // 座標の取得と算出
        int sceneHalfWidth = GameController.getSceneWidth() / 2;
        int sceneHalfHeight = GameController.getSceneHeight() / 2;

        int drawPaneX =  sceneHalfWidth - pos.x;
        int drawPaneY =  sceneHalfHeight - pos.y;

        // 描画パネルの移動　
        transfer(drawPaneX, drawPaneY);
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
        } else {  // それ以外のときは、キャラクターの中心座標で判定
            return rangeOfActivities.contains(go.getPosition());
        }
    }

    public boolean checkBeingShown(Point pos) {
        return rangeOfActivities.contains(pos);
    }

    /**
     * 基準点を中心に取るように有効範囲を移動
     *
     * @param pos the pos
     */
    public void focusPointForRangeOfActivities(Point pos) {
        // 座標の取得と算出
        int sceneHalfWidth = GameController.getSceneWidth() / 2;
        int sceneHalfHeight = GameController.getSceneHeight() / 2;

        int rangeX =  pos.x - sceneHalfWidth - RANGE_MARGIN;
        int rangeY =  pos.y - sceneHalfHeight - RANGE_MARGIN;

        transferRangeOfActivities(rangeX, rangeY);
    }

    /**
     * オブジェクトの有効範囲の更新
     */
    private void transferRangeOfActivities(int x, int y) {

        // 描画パネル上の座標を基準にした
        int minX = -RANGE_MARGIN, minY = -RANGE_MARGIN;  // 下限
        int maxX = (int)drawPane.getWidth() - GameController.getSceneWidth() - RANGE_MARGIN;  // 上限
        int maxY = (int)drawPane.getHeight() - GameController.getSceneHeight() - RANGE_MARGIN;

        // 補正
        if ( x < minX ) { x = minX; } else if ( x > maxX ) { x = maxX; }
        if ( y < minY ) { y = minY; } else if ( y > maxY ) { y = maxY; }

        // 有効範囲の設定
        rangeOfActivities = new Rectangle(x, y,
                GameController.getSceneWidth() + RANGE_MARGIN * 2, GameController.getSceneHeight() + RANGE_MARGIN * 2);
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

    public Rectangle getRangeOfActivities() {
        return rangeOfActivities;
    }
}
