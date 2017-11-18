package application.component.system;

/**
 * ゲームの環境値を管理するシングルトンなクラス
 */
public class GameEnvironment {
    private static final GameEnvironment ourInstance = new GameEnvironment();
    private static final double DEFAULT_GRAVITY = 9.8d;   // 標準の重力
    private static final int DEFAULT_BLOCK_SCALE = 50;    // 標準の1区画サイズ

    private double gravity = DEFAULT_GRAVITY;       // 重力
    private int blockScale = DEFAULT_BLOCK_SCALE;  // 1区画サイズ

    public static GameEnvironment getInstance() {
        return ourInstance;
    }

    private GameEnvironment() {

    }

    public static double getGravity() {
        return ourInstance.gravity;
    }

    public void setGravity(double gravity) {
        if ( gravity < 0.0d ) { gravity = DEFAULT_GRAVITY; }
        this.gravity = gravity;
    }

    public static int getBlockScale() {
        return ourInstance.blockScale;
    }

    public void setBlockScale(int blockScale) {
        this.blockScale = blockScale;
    }
}
