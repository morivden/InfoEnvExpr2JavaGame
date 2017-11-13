package application.component.system;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class InputManager {
    private HashMap<KindOfPushedKey, AtomicBoolean> isPushed;  // 押されているかどうか

    public InputManager() {
        initStates();
    }

    /**
     * 状態の初期化
     */
    private void initStates() {
        isPushed = new HashMap<>();  // 初期化
        Arrays.stream(KindOfPushedKey.values()).forEach(key -> isPushed.put(key, new AtomicBoolean(false)));  // 全ての状態をfalseで登録
    }

    /**
     * 状態の格納
     *
     * @param key
     * @param state
     */
    public void set(KindOfPushedKey key, boolean state) {
        isPushed.get(key).set(state);
    }

    /**
     * 状態の取得
     *
     * @param key
     * @return
     */
    public boolean get(KindOfPushedKey key) {
        return isPushed.get(key).get();
    }

    /**
     * 受け付けるキーの種類
     */
    public enum KindOfPushedKey { UP_KEY, DOWN_KEY, LEFT_KEY, RIGHT_KEY, SPACE_KEY }
}
