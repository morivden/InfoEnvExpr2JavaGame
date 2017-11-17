package application.component.system;

/**
 * ゲームの環境値を管理するシングルトンなクラス
 */
public class GameEnvironment {
    private static final GameEnvironment ourInstance = new GameEnvironment();
    private static final double DEFAULT_GRAVITY = 9.8d;   // 標準の重力

    private double gravity = DEFAULT_GRAVITY;      // 重力

    public static GameEnvironment getInstance() {
        return ourInstance;
    }

    private GameEnvironment() {

    }

    public double getGravity() {
        return gravity;
    }

    void setGravity(double gravity) {
        if ( gravity < 0.0d ) { gravity = DEFAULT_GRAVITY; }
        this.gravity = gravity;
    }
}
