package application.component.objects.character;

import java.util.List;

public interface OffensiveObject {

    /**
     * リストに格納された全てのOffensiveObjectの移動
     *
     * @param offensiveObjects
     */
    static void attackOffensiveObjects(List<OffensiveObject> offensiveObjects) {
        offensiveObjects.forEach(oo -> oo.attack());
    }

    /**
     * 攻撃メソッド
     */
    void attack();
}
