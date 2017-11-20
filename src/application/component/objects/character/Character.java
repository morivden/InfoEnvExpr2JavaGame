package application.component.objects.character;

import application.component.objects.GameObject;

import java.awt.*;

public abstract class Character extends GameObject {
    protected int hp;

    public Character(Point pos) {
        super(pos);
    }

    public int getHp() {
        return hp;
    }

    /**
     * hp に引数の値だけダメージを与え、残りHPを返却すr
     *
     * @param damageVal ダメージ値
     * @return
     */
    public int damage(int damageVal) {
        hp -= damageVal;
        return hp;
    }
}
