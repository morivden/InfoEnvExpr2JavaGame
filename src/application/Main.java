package application;

import application.controller.GameController;
import application.controller.TitleController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static final int WINDOW_HEIGHT = 400;
    public static final int WINDOW_WIDTH = 600;

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;

            this.primaryStage.setTitle("JavaGame");  // タイトル設定
            this.primaryStage.setResizable(false);   // ウィンドウサイズの固定
            this.primaryStage.setMaximized(false);   // 最大化の無効

            TitleController.getInstance().show();  // タイトル部分のシーンを挿入
            this.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
