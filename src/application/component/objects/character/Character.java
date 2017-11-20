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
}
