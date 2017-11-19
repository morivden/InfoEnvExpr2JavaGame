package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleController implements Initializable {

	@FXML private Pane root;
    @FXML private Text gameTitle;
    @FXML private Button startButton;
    @FXML private Button configBottun;

    private static final TitleController tc;  // TitleControllerインスタンス
    private static final Scene SCENE;
    private static File file = new File("resources//bgm//02 Phantom.mp3");
    private static Media media = new Media(file.toURI().toString());
    private static MediaPlayer mediaPlayer = new MediaPlayer(media);
    
    static  {
        FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("fxml/TitleController.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);
        mediaPlayer.play();

        SCENE = scene;
        tc = fxmlLoader.getController();
    }

    public static TitleController getInstance() {
        return tc;
    }
    
    public static MediaPlayer getMediaPlayer() {
    	return mediaPlayer;
    }

    public void show() {
        TitleControllerMain.primaryStage.setScene(SCENE);
    }
    
    @FXML
    public void clickStartButton(ActionEvent event) {          // startButtonを押した時に実行するアクションイベント
    	GameController.getInstance().show();
    }
    @FXML
    public void clickConfigButton(ActionEvent event) {          // configButtonを押した時に実行するアクションイベント
    	ConfigController.getInstance().show();
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		root.setEffect(ConfigController.getScreenLight());
	}
}
