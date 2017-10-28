package application;

import application.controller.GameController;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

    public static final int WINDOW_HEIGHT = 400;
    public static final int WINDOW_WIDTH = 400;
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;

            this.primaryStage.setTitle("JavaGame");  // タイトル設定

            GameController.getInstance().show();  // シーンの挿入
            this.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
