package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML private AnchorPane root;
    @FXML private Pane drawPane;

    private static final GameController gc;  // GameControllerインスタンス
    private static final Scene SCENE;

    static  {
        FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("fxml/GameController.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);

        SCENE = scene;
        gc = fxmlLoader.getController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawPane.setFocusTraversable(true);
        drawPane.requestFocus();

        if ( drawPane.isFocused() ) {
            System.out.println("aa");
        }
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
        switch ( event.getCode() ) {
            case UP :
                System.out.println("うえ");      break;
            case DOWN :
                System.out.println("した");      break;
            case LEFT :
                System.out.println("ひだり");    break;
            case RIGHT :
                System.out.println("みぎ");      break;
            case SPACE :
                System.out.println("すぺーす");  break;
            default :
                System.out.println("なし");      break;
        }
    }

    /**
     * キーボードのキーが離された時の処理
     *
     * @param event the event
     */
    public void onKeyReleased(KeyEvent event) {
        System.out.print("Released: ");
        switch ( event.getCode() ) {
            case UP :
                System.out.println("うえ");      break;
            case DOWN :
                System.out.println("した");      break;
            case LEFT :
                System.out.println("ひだり");    break;
            case RIGHT :
                System.out.println("みぎ");      break;
            case SPACE :
                System.out.println("すぺーす");  break;
            default :
                System.out.println("なし");      break;
        }
    }
}
