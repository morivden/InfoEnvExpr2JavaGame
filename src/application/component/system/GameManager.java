package application.component.system;

import application.component.map.GameMap;
import application.component.map.MapFactory;
import application.component.map.MapFactory.IllegalMapDataException;
import application.controller.GameController;
import javafx.scene.layout.Pane;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ゲームの管理クラス (シングルトン)
 */
public class GameManager {
    private static final GameManager ourInstance = new GameManager();
    private static final int PROCESS_INTERVAL_MILLISECOND = 500;  // 処理の間隔

    private int stageNum;     // 現在選択肢ているステージ番号
    private Pane drawPane;    // 使用するパネル

    private AtomicBoolean is_continuing = new AtomicBoolean(false);  // ゲームの持続判定
    private GameProcessTask gpt;    // ゲームプロセス
    private Timer gameTimer;        // ゲームプロセス用タイマー

    private GameMap gameMap;        // マップ
    private DrawPanelManager dpm;   // パネル管理
    // TODO Playerクラスのフィールドを作成

    public static GameManager getInstance(Pane drawPane, int stageNum) {
        ourInstance.stageNum = stageNum;
        ourInstance.drawPane = drawPane;
        return ourInstance;
    }

    /**
     * 開始メソッド
     */
    public void start() {
        // 実行中でなければ、開始
        if ( gameTimer == null ) {
            try {
                exec();             // ゲーム実行
            } catch ( NotGameProcessException e ) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 停止メソッド
     */
    public void stop() {
        // ゲームが実行中であれば、停止
        if ( gameTimer != null ) {
            gameTimer.cancel();  // 停止
            gameTimer.purge();   // タスクの消去
            gameTimer = null;    // 破棄
        }
    }

    /**
     * 現在のゲーム状態を削除
     */
    public void delete() {
        stop();  // 停止
        is_continuing.set(false);  // ゲームの状態の継続フラグを下ろす
    }

    /**
     * 実行メソッド
     */
    private void exec() throws NotGameProcessException {
        // ゲーム状態の新規作成判定
        if ( is_continuing.get() ) {
            initializeGameComponent(stageNum);  // 初期化
            is_continuing.set(true);            // ゲーム状態の継続フラグを立てる
        }

        // 実行
        gameTimer = new Timer("game_timer", true);
        gpt = new GameProcessTask();
        gameTimer.scheduleAtFixedRate(gpt, 0, PROCESS_INTERVAL_MILLISECOND);  // 実行
    }

    /**
     * ゲーム内要素の初期化
     */
    private void initializeGameComponent(int stageNum) {
        try {
            gameMap = MapFactory.createMap(stageNum);
        } catch ( IllegalMapDataException | IllegalArgumentException e ) {
            e.printStackTrace();
            // TODO 例外が発生した場合の仮マップを用意する。仮マップは、GameFactoryクラスかGameMapクラスのクラスメソッドから取得する
        }
        // TODO 生成したMapクラスのインスタンスからPlayerクラスのインスタンスを取得し、Playerフィールドに格納
        inputObjectsToPane();    // drawPaneの初期化
    }

    /**
     * GameMapクラスのインスタンスが持つImageViewをdrawPaneに登録する
     */
    private void inputObjectsToPane() {
        // TODO 実装する
    }

    /**
     * ゲーム部クラス
     */
    private class GameProcessTask extends TimerTask {
        @Override
        public void run() {
            // TODO 各ゲームプロセスの実装
            //== ファクトリーの更新
            //== キャラクターの更新
            //== 移動オブジェクトの更新
            //== 攻撃オブジェクトの更新
            //== 衝突オブジェクトの反映
            //== 描画パネル(drawPane)の移動
            //== 無効キャラクターの削除
            //== ゲーム終了判定
        }
    }

    /**
     * 描画パネル管理クラス
     */
    public class DrawPanelManager {
        private Pane drawPanel;

        public DrawPanelManager(Pane drawPanel) {
            this.drawPanel = drawPanel;
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
        // TODO GameObject実装後、実装2
        public void inputGameObject() {

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
            double minX = drawPane.getWidth() - GameController.getSceneWidth();    // 下限
            double minY = drawPane.getHeight() - GameController.getSceneHeight();  // 下限

            // 補正
            if ( x < minX ) { x = minX; } else if ( x > maxX ) { x = maxX; }
            if ( y < minY ) { y = minY; } else if ( y > maxY ) { y = maxY; }

            // 格納
            drawPane.setTranslateX(x);
            drawPane.setTranslateY(y);
        }
    }

    /**
     * GameProcessクラスのインスタンスが用意されていないとき発生する例外
     */
    private class NotGameProcessException extends Exception {
        public NotGameProcessException() {
            super();
        }
    }
}
