package application.component.system;

import application.component.map.GameMap;
import application.component.objects.GameObject;
import application.component.objects.character.implement_character.TMPCharacter;
import application.controller.GameController;
import javafx.scene.layout.Pane;

/**
 * 描画パネル管理クラス
 */
public class DrawPanelManager {
    private Pane drawPane;

    public DrawPanelManager(Pane drawPane) {
        this.drawPane = drawPane;
    }

    /**
     * マップの反映
     *
     * @param gm the gm
     */
    public void inputMap(GameMap gm) {
        drawPane.getChildren().removeAll();  // 既存要素の削除
        // TODO GameObject実装後、実装
//            List<GameObject> nodes = gm.getGameObject();
//            nodes.stream().forEach(n -> { drawPane.getChildren().add(n) });
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
        drawPane.getChildren().add(tp.getImg());
    }

    /**
     * パネル(の左上端座標)を親ノードのローカル座標上の指定の座標に移動
     * また、移動時には、パネルが画面全てを覆うように補正を行う。
     *
     * @param x x座標
     * @param y y座標
     */
    public void transfer(double x, double y) {
        double maxX = 0, maxY = 0;  // 上限
        double minX = GameController.getSceneWidth() - drawPane.getWidth();    // 下限
        double minY = GameController.getSceneHeight() - drawPane.getHeight();  // 下限

        // 補正
        if ( x < minX ) { x = minX; } else if ( x > maxX ) { x = maxX; }
        if ( y < minY ) { y = minY; } else if ( y > maxY ) { y = maxY; }

        // 格納
        drawPane.setLayoutX(x);
        drawPane.setLayoutY(y);;
    }
}
