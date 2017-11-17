package application.component.system;

/**
 * ゲームの環境値を管理するシングルトンなクラス
 */
public class GameEnvironment {
    private static GameEnvironment ourInstance = new GameEnvironment();
    private static double DEFAULT_GRAVITY = 9.8d;   // 標準の重力
    private static int DEFAULT_BLOCK_SCALE = 50;    // 標準の1区画のサイズ

    private double gravity = DEFAULT_GRAVITY;      // 重力
    private int blockScale = DEFAULT_BLOCK_SCALE;  // 区間サイズ

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

    public int getBlockScale() {

        return blockScale;
    }

    void setBlockScale(int blockScale) {
        this.blockScale = blockScale;
    }
}
