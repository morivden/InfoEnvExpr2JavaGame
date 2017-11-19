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

public class ConfigController {

    @FXML private Text config;
    @FXML private Text volume;
    @FXML private Text contrast;
    @FXML private Slider volumeBar;
    @FXML private Slider contrastBar;
    @FXML private Button backButton;
    
	private static final ConfigController cc;  // ConfigControllerインスタンス
    private static final Scene SCENE;
    
    static {
    	FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("fxml/ConfigController.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);

        SCENE = scene;
        cc = fxmlLoader.getController();
    }
    public static ConfigController getInstance() {
        return cc;
    }

    public void show() {
        //ConfigControllerMain.primaryStage.setScene(SCENE);
        TitleControllerMain.primaryStage.setScene(SCENE);                 // 画面遷移を確認するために一時的に変更
    }
    @FXML
    public void clickBackButton(ActionEvent event) {          // backButtonを押した時に実行するアクションイベント
    	TitleController.getInstance().show();
    }
}
