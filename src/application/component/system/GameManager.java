package application.component.system;

import application.component.map.GameMap;
import application.component.map.MapFactory;
import application.component.map.MapFactory.IllegalMapDataException;
import javafx.scene.layout.Pane;

import java.util.Timer;
import java.util.TimerTask;

import static application.component.system.GameManager.GameProcessState.*;

/**
 * ゲームの管理クラス (シングルトン)
 */
public class GameManager {
    private static final GameManager ourInstance = new GameManager();
    private static final int PROCESS_INTERVAL_MILLISECOND = 30;           // 処理の間隔
    private static final InputManager inputManager = new InputManager();  // 入力キー管理

    private int stageNum;     // 現在選択肢ているステージ番号
    private Pane drawPane;    // 使用するパネル

    private GameProcessState gameState = GameProcessState.STOP;  // ゲームの状態
    private GameProcessTask gpt;    // ゲームプロセス
    private Timer gameTimer;        // ゲームプロセス用タイマー

    private GameMap gameMap;        // マップ
    private DrawPanelManager dpm;   // パネル管理
    // TODO Playerクラスのフィールドを作成

    /**
     * インスタンスの取得
     *
     * @param drawPane the draw pane
     * @param stageNum the stage num
     * @return the instance
     */
    public static GameManager getInstance(Pane drawPane, int stageNum) {
        ourInstance.stageNum = stageNum;
        ourInstance.drawPane = drawPane;
        return ourInstance;
    }

    public static InputManager getInputManager() {
        return inputManager;
    }

    public static void setKeyState(InputManager.KindOfPushedKey key, boolean state) {
        inputManager.set(key, state);
    }

    public static boolean getKeyState(InputManager.KindOfPushedKey key) {
        return inputManager.get(key);
    }

    /**
     * 開始メソッド
     */
    public void start() {
        // 実行中でなければ、開始
        if ( gameState != RUN ) {
            try {
                exec();             // ゲーム実行
            } catch ( NotGameProcessException e ) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 一時停止メソッド
     */
    public void pause() {
        // ゲームが実行中であれば、停止
        if ( gameState == RUN ) {
            gameTimer.cancel();  // 停止
            gameTimer.purge();   // タスクの消去
            gameTimer = null;    // 破棄
            gameState = PAUSE;   // 状態の更新
        }
    }

    /**
     * 停止メソッド
     */
    public void stop() {
        pause();  // 停止
        gameState = STOP;  // 状態の更新
    }

    public Pane getDrawPane() {
        return drawPane;
    }

    /**
     * 実行メソッド
     */
    private void exec() throws NotGameProcessException {
        // ゲーム状態の新規作成判定
        if ( gameState == STOP ) {
            initializeGameComponent(stageNum);  // 初期化
        }

        // 実行
        gameTimer = new Timer("game_timer", true);
        gpt = new GameProcessTask();
        gameTimer.scheduleAtFixedRate(gpt, 0, PROCESS_INTERVAL_MILLISECOND);  // 実行
        gameState = RUN;                    // 状態の更新
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
        inputObjectsToPane();    // 描画パネル関連の初期化
    }

    /**
     * GameMapクラスのインスタンスが持つGaneObjectoのインスタンスのImageViewをdrawPaneに登録する
     */
    private void inputObjectsToPane() {
        dpm = new DrawPanelManager(drawPane);
        // TODO 実装する
    }

    /**
     * ゲームの起動状況判別用列挙体
     */
    public enum GameProcessState {
        RUN, STOP, PAUSE
    }

    /**
     * GameProcessクラスのインスタンスが用意されていないとき発生する例外
     */
    private class NotGameProcessException extends Exception {
        public NotGameProcessException() {
            super();
        }
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
            moveDrawPanel();

            //== 無効キャラクターの削除
            //== ゲーム終了判定
        }

        private void moveDrawPanel() {
            double nextX = drawPane.getTranslateX();
            double nextY = drawPane.getTranslateY();
            // TODO 今後、Playerのキャラクターの座標に追随するように変更予定
            if ( inputManager.get(InputManager.KindOfPushedKey.UP_KEY) ) {
                nextY -= 10;
            } else if ( inputManager.get(InputManager.KindOfPushedKey.DOWN_KEY) ) {
                nextY += 10;
            }
            if ( inputManager.get(InputManager.KindOfPushedKey.LEFT_KEY) ) {
                nextX -= 10;
            } else if ( inputManager.get(InputManager.KindOfPushedKey.RIGHT_KEY) ) {
                nextX += 10;
            }
            dpm.transfer(nextX, nextY);
        }
    }
}
