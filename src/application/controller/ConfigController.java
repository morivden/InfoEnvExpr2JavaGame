package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.input.MouseEvent;

import java.io.File;
import javafx.scene.media.*;
import javafx.scene.control.Slider;
import javafx.beans.value.ObservableValue;

import javafx.scene.effect.ColorAdjust;

public class ConfigController implements Initializable {

    @FXML private Pane root;
    @FXML private Text config;
    @FXML private Text volume;
    @FXML private Text contrast;
    @FXML private Slider volumeBar;
    @FXML private Slider contrastBar;
    @FXML private Button backButton;
    
	private static final ConfigController cc;  // ConfigControllerインスタンス
    private static final Scene SCENE;
    private static ColorAdjust screenLight = new ColorAdjust();
    
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
    
    public static ColorAdjust getScreenLight() {
    	return screenLight;
    }

    public void show() {
        //ConfigControllerMain.primaryStage.setScene(SCENE);
        TitleControllerMain.primaryStage.setScene(SCENE);                 // 画面遷移を確認するために一時的に変更
    }
    
    
    @FXML
    public void clickBackButton(ActionEvent event) {          // backButtonを押した時に実行するアクションイベント
    	TitleController.getInstance().show();
    }
    @FXML
    public void changeVolume(MouseEvent event) {             // VolumeのSliderを変更した時に実行するアクションイベント
    	TitleController.getMediaPlayer().setVolume(volumeBar.getValue());
    }
    @FXML
    public void changeContrast(MouseEvent event) {           // ContrastのSliderを変更した時に実行するアクションイベント
    	screenLight.setBrightness(contrastBar.getValue());
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		volumeBar.setValue(TitleController.getMediaPlayer().getVolume());
		contrastBar.setValue(screenLight.getBrightness());
		root.setEffect(screenLight);
	}
}
