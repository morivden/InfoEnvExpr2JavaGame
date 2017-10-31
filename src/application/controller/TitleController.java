package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.io.IOException;

public class TitleController {

    @FXML private Text gameTitle;
    @FXML private Button startButton;
    @FXML private Button configBottun;

    private static final TitleController tc;  // GameControllerインスタンス
    private static final Scene SCENE;

    static  {
        FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("fxml/TitleController.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);

        SCENE = scene;
        tc = fxmlLoader.getController();
    }

    public static TitleController getInstance() {
        return tc;
    }

    public void show() {
        TitleControllerMain.primaryStage.setScene(SCENE);
    }
}
