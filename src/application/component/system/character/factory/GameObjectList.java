package application.component.system.character.factory;

import application.component.objects.GameObject;
import application.component.objects.stage.implement_stage.TMPStageBlock;
import com.sun.javafx.geom.Point2D;

import java.util.Optional;

/**
 * 生成されるゲームオブジェクトリスト
 */
public enum GameObjectList {
    // オブジェクト一覧
    Hero('0', pos -> { return null; }),
    Monster('1', pos -> { return null; }),
    TMPStage('@', pos -> { return new TMPStageBlock(pos); });

    private char identificationString;     // 識別用文字列

    public char getIdentificationString() {
        return identificationString;
    }

    private CharacterCreator createProcess;  // 生成用

    GameObjectList(char identificationString, CharacterCreator cc) {
        this.identificationString = identificationString;
        this.createProcess = cc;
    }

    /**
     * 指定の識別文字列に対応するゲームオブジェクトを生成する
     *
     * @param iStr 識別文字列
     * @param pos  生成位置
     * @return game object
     */
    public static GameObject createOf(char iStr, Point2D pos) {
        GameObjectList gol = getOf(iStr).orElse(TMPStage);
        return gol.createProcess.getInstance(pos);
    }

    /**
     * 指定の識別文字列に対応する要素を取得
     *
     * @param iStr 識別文字列
     * @return of
     */
    public static Optional<GameObjectList> getOf(char iStr) {
        for ( GameObjectList goList : GameObjectList.values() ) {
            if ( goList.equals(iStr) ) { return Optional.of(goList); }
        }

        return Optional.empty();
    }

    /**
     * 識別文字列による等価判定
     *
     * @param iStr 判別したい識別用文字列
     * @return
     */
    public boolean equals(char iStr) {
        return identificationString == iStr;
    }

    /**
     * インスタンスの取得
     *
     * @param pos キャラクターの位置
     * @return the instance
     */
    public GameObject getInstance(Point2D pos) {
        return this.createProcess.getInstance(pos);
    }

    /**
     * ゲームオブジェクト生成用のSAMインタフェース
     */
    public interface CharacterCreator {
        GameObject getInstance(Point2D createPos);
    }
}
