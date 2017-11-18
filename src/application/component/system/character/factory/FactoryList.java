package application.component.system.character.factory;

import application.component.objects.GameObject;
import application.component.objects.character.PlayableCharacter;
// import com.sun.javafx.geom.Point2D;
import java.awt.Point;

/**
 * ファクトリークラスにより、生成されるゲームオブジェクトリスト
 */
public enum FactoryList {

    // オブジェクト一覧
    Hero("+", pos -> { return null; }),
    Moster("-", pos -> { return null; });

    private String identificationString;     // 識別用文字列
    private CharacterCreator createProcess;  // 生成用

    FactoryList(String identificationString, CharacterCreator cc) {
        this.identificationString = identificationString;
        this.createProcess = cc;
    }

    /**
     * 識別文字列による等価判定
     *
     * @param iStr 判別したい識別用文字列
     * @return
     */
    public boolean equals(String iStr) {
        return identificationString.equals(iStr);
    }

    /**
     * インスタンスの取得
     *
     * @param pos キャラクターの位置
     * @return the instance
     */
    public PlayableCharacter getInstance(Point pos) {
        return this.createProcess.getInstance(pos);
    }

    /**
     * ゲームオブジェクト生成用のSAMインタフェース
     */
    public interface CharacterCreator {
        public PlayableCharacter getInstance(Point createPos);
    }
}
