package application.controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class ConfigControllerMain extends Application {

    public static final int WINDOW_HEIGHT = 400;
    public static final int WINDOW_WIDTH = 600;
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;
            this.primaryStage.setWidth(WINDOW_WIDTH);
            this.primaryStage.setHeight(WINDOW_HEIGHT);
            this.primaryStage.setTitle("JavaGame");  // タイトル設定

            ConfigController.getInstance().show();  // シーンの挿入
            this.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

