package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.io.IOException;

public class GameController {
    private static final GameController gc;
    private static final Scene SCENE;

    static  {
        FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("fxml/GameController.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            // loggerを使って何かしら出すほうがいいですね。手抜きですみません
            e.printStackTrace();
        }
        Parent parent = fxmlLoader.getRoot();
        Scene s = new Scene(parent);

        SCENE = s;
        s.setFill(Color.RED);
        gc = fxmlLoader.getController();
    }

    public static GameController getInstance() {
        return gc;
    }

    public void show() {
        Main.primaryStage.setScene(SCENE);
    }

//    @FXML
//    public void onClick(ActionEvent event) {
//        // クリック時の処理とか色々して~~~次のページ表示
//        FirstContent.getInstance().show();
//    }
}
