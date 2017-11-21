package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectStageController implements Initializable {
	
	@FXML private Pane root;
    @FXML private Text textTitle;
    @FXML private Button stage1Button;
    @FXML private Button stage2Button;
    @FXML private Button stage3Button;
    
    private static final SelectStageController ssc;  // SelectStageControllerインスタンス
    private static final Scene SCENE;
    
	static  {
        FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("fxml/SelectStageController.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);

        SCENE = scene;
        ssc = fxmlLoader.getController();
    }
	
	public static SelectStageController getInstance() {
        return ssc;
    }
	
	public void show() {
    	textTitle.setFont(Font.loadFont(ClassLoader.getSystemResource("font/yukarimobil.ttf").toString(), 55)); // タイトルのフォントを外部フォントに指定
    	Main.primaryStage.setScene(SCENE);                 // 画面遷移を確認するために一時的に変更
    }

	@FXML
    public void clickButton1(ActionEvent event) {               // stage1Buttonを押した時に実行するアクションイベント
    	GameController.getInstance(1).show();
    }
    @FXML
    public void clickButton2(ActionEvent event) {          // stage2Buttonを押した時に実行するアクションイベント
    	GameController.getInstance(2).show();
    }
    @FXML
    public void clickButton3(ActionEvent event) {          // stag3Buttonを押した時に実行するアクションイベント
    	GameController.getInstance(3).show();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		root.setEffect(ConfigController.getScreenLight());
	}
}
