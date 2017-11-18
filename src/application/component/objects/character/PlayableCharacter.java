package application.component.objects.character;

import java.awt.*;

public abstract class PlayableCharacter extends Character implements MovableObject, OffensiveObject {
    protected Point speed = new Point();  // キャラクタの移動スピード

    public PlayableCharacter(Point pos) {
        super(pos);
    }

    public void setSpeed(int speedX, int speedY) {
        speed.setLocation(speedX, speedY);
    }
    
    public int getXSpeed() {
        return speed.x;
    }
    
    public int getYSpeed() {
        return speed.y;
    }
}
