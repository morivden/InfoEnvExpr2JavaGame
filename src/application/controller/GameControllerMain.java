package application.controller;

import application.component.map.MapFactory;
import application.component.map.MapInfomation;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameControllerMain extends Application {

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

            GameController.getInstance().show();  // シーンの挿入
            this.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MapFactory.createMap(1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

