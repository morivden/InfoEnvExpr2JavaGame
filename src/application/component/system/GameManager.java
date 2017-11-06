package application.component.system;

import application.component.map.GameMap;
import application.component.map.IllegalMapDataException;
import application.component.map.MapFactory;
import javafx.scene.layout.Pane;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ゲームの管理クラス
 */
public class GameManager {
    private static final GameManager ourInstance = new GameManager();
    private static final int PROCESS_INTERVAL_MILLISECOND = 500;  // 処理の間隔

    private int stageNum;     // 現在選択肢ているステージ番号
    private Pane drawPane;    // 使用するパネル

    private AtomicBoolean running;  // 実行フラグ
    private GameProcess gp;         // ゲームプロセス

    private GameMap gameMap;        // マップ
    // TODO Playerクラスのフィールドを作成

    private GameManager() {
        running = new AtomicBoolean(false);
    }

    public static GameManager getInstance(Pane drawPane, int stageNum) {
        ourInstance.stageNum = stageNum;
        ourInstance.drawPane = drawPane;
        return ourInstance;
    }

    /**
     * 開始メソッド
     */
    public void start() {
        if ( !running.get() ) { return; }  // 実行中かどうか判定
        running.set(true);  // フラグ更新

        try {
            exec();             // ゲーム実行
        } catch (NotGameProcessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止メソッド
     */
    public void stop() {
        running.set(false);  // フラグ更新
    }

    /**
     * 現在保持しているゲームの状態を削除
     */
    public void delete() {
        stop();      // 停止
        gp = null;   // 現在のデータを削除
    }

    /**
     * 実行メソッド
     *
     */
    private void exec() throws NotGameProcessException {
        if ( gp == null ) { initializeGameComponent(stageNum); }  // ゲーム実行
        gp.start();
    }

    /**
     * ゲーム内要素の初期化
     */
    private void initializeGameComponent(int stageNum) {
        try {
            gameMap = MapFactory.createMap(stageNum);
        } catch (IllegalMapDataException e) {
            e.printStackTrace();
            // TODO 例外が発生した場合の仮マップを用意する。仮マップは、GameFactoryクラスかGameMapクラスのクラスメソッドから取得する
        }
        // TODO 生成したMapクラスのインスタンスからPlayerクラスのインスタンスを取得し、Playerフィールドに格納
        inputObjectsToPane();    // drawPaneの初期化
        gp = new GameProcess();  // 新しい実行クラスのインスタンスの生成
    }

    /**
     * GameMapクラスのインスタンスが持つImageViewをdrawPaneに登録する
     *
     */
    private void inputObjectsToPane() {
        // TODO 実装する
    }

    /**
     * ゲーム部クラス
     */
    private class GameProcess extends Thread {
        @Override
        public void run() {
            while ( running.get() ) {
                // TODO 各ゲームプロセスの実装
                //== ファクトリーの更新
                //== キャラクターの更新
                //== 移動オブジェクトの更新
                //== 攻撃オブジェクトの更新
                //== 衝突オブジェクトの反映
                //== 無効キャラクターの削除
                //== ゲーム終了判定

                //== 待ち
                try {
                    sleep(PROCESS_INTERVAL_MILLISECOND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
