package application.component.objects.character;

public abstract class PlayableCharacter extends Character implements MovableObject, OffensiveObject {
    public PlayableCharacter  () {
        
    }
    
    public abstract void setSpeed(int x_speed, int y_speed);
    
    public abstract int getX_speed();
    
    public abstract int getY_speed();
}
