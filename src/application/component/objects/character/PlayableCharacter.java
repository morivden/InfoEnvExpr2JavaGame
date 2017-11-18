package application.component.objects.character;

import java.awt.*;

public abstract class PlayableCharacter extends Character implements MovableObject, OffensiveObject {
    protected Point speed = new Point();  // キャラクタの移動スピード

    public PlayableCharacter(Point pos) {
        super(pos);
    }
    
    public int getXSpeed() {
        return speed.x;
    }
    
    public int getYSpeed() {
        return speed.y;
    }

    protected void updateImage() {
        Point relDist = getCollisionRelativeDistance();
        imageManager.transfer(position.x + relDist.x, position.y + relDist.y);
    }

    /**
     * キャラクターの基準位置とイメージの左上端位置の相対距離を取得
     *
     * @return
     */
    protected abstract Point getCollisionRelativeDistance();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Position: (%d, %d)\n", position.x, position.y));
        sb.append(String.format("CollisionPosition: %s\n", collisionObject.toString()));

        return new String(sb);
    }
}
