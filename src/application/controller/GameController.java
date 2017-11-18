package application.controller;

import application.component.system.GameManager;
import application.component.system.InputManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameController implements Initializable {

    @FXML
    protected AnchorPane root;
    @FXML
    protected Pane drawPane;

    protected static final GameController gc;  // GameControllerインスタンス
    protected static final Scene SCENE;
    protected GameManager gameManager;

    static {
        FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("fxml/GameController.fxml"));
        try {
            fxmlLoader.load();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);

        SCENE = scene;
        gc = fxmlLoader.getController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // drawPaneにフォーカスを移す
        drawPane.setFocusTraversable(true);
        drawPane.requestFocus();

        gameManager = GameManager.getInstance(drawPane, 1);
        gameManager.start();

        // TODO あとで消す、位置確認用の球ノード
        drawPane.getChildren().add(new Circle(400/2, 600/2,100));
    }

    public static GameController getInstance() {
        return gc;
    }

    public void show() {
        GameControllerMain.primaryStage.setScene(SCENE);
    }

    /**
     * キーボードのキーが押された時の処理
     *
     * @param event the event
     */
    @FXML
    public void onKeyPressed(KeyEvent event) {
        System.out.print("Pressed  ");
        changeKeyState(event, true);
    }

    /**
     * キーボードのキーが離された時の処理
     *
     * @param event the event
     */
    public void onKeyReleased(KeyEvent event) {
        System.out.print("Released: ");
        changeKeyState(event, false);
    }

    /**
     * キーの状態更新メソッド
     *
     * @param event
     */
    protected void changeKeyState(KeyEvent event, boolean state) {
        switch ( event.getCode() ) {
            case UP:
                GameManager.getInputManager().set(InputManager.KindOfPushedKey.UP_KEY, state);
                System.out.println("うえ"); break;
            case DOWN:
                GameManager.getInputManager().set(InputManager.KindOfPushedKey.DOWN_KEY, state);
                System.out.println("した"); break;
            case LEFT:
                GameManager.getInputManager().set(InputManager.KindOfPushedKey.LEFT_KEY, state);
                System.out.println("ひだり"); break;
            case RIGHT:
                GameManager.getInputManager().set(InputManager.KindOfPushedKey.RIGHT_KEY, state);
                System.out.println("みぎ"); break;
            case SPACE:
                GameManager.getInputManager().set(InputManager.KindOfPushedKey.SPACE_KEY, state);
            default:
                System.out.println("なし"); break;
        }
    }

    /**
     * シーンの幅を取得
     *
     * @return the scene width
     */
    public static int getSceneWidth() {
        return (int)SCENE.getWidth();
    }

    /**
     * シーンの高さを取得
     *
     * @return the scene height
     */
    public static int getSceneHeight() {
        return (int)SCENE.getHeight();
    }
}
