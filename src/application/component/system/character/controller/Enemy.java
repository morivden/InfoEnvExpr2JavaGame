package application.component.system.character.controller;

import application.component.objects.character.PlayableCharacter;
import application.component.system.GameEnvironment;
import application.component.system.GameManager;
// import com.sun.javafx.geom.Point2D;

import java.awt.Point;
import java.util.Optional;
import java.util.Random;

public class Enemy extends CharacterController {
    public static final int SPEED_CHANGE_INTERVAl = 2000;  // 方向転換の間隔 (ms)
    public static final double ACCELERATE_VALUE = 0.5;     // 加速値
    public final int DEFAULT_WAIT_SPEED;                   // 待機状態の移動スピード
    public final int RAND_RANGE = 6;                       // ランダム値の振れ幅

    private PlayableCharacter character;

    private Random rand = new Random();
    private long previousChangeSpeedTime;  // 以前のスピード更新時間

    int speedX;  // 現在のX方向のスピード
    int speedY;  // 現在のY方向のスピード

    public Enemy(PlayableCharacter character) {
        this.character = character;
        DEFAULT_WAIT_SPEED = character.getDefaultSpeed() / 5;
        speedX = DEFAULT_WAIT_SPEED;
        speedY = DEFAULT_WAIT_SPEED + (int)GameEnvironment.getGravity();
        this.character.setOnGround(false);
    }

    @Override
    protected void updateSpeed() {
        Optional<Player> playerCharacterController = GameManager.getPlayerCharacterController();

        if ( playerCharacterController.isPresent() ) {
            Point playerPos = playerCharacterController.get().getCharacter().getPosition();
            //== プレイヤーキャラクターが索敵範囲にいる場合
            if ( playerPos.distance(character.getPosition()) < character.getRange() && this.character.isOnGround()) {
                int distanceX = playerPos.x - character.getPosition().x;
                int distanceY = playerPos.y - character.getPosition().y;
                speedX = character.getDefaultSpeed() * (int)Math.signum(distanceX);
                speedY = character.getDefaultSpeed() * (int)Math.signum(distanceY) + (int)GameEnvironment.getGravity();

                if ( Math.abs(speedX) > Math.abs(distanceX) ) {
                    speedX = distanceX;
                }
                speedX *= ACCELERATE_VALUE;  // 原則処理
            } else {  //== 索敵範囲にいない場合
                long currentChangeSpeedTime = System.currentTimeMillis();  // 最新のスピード更新時間

                // 前回のスピードに基づいて、移動方向を設定
                if ( speedX < 0 ) {
                    speedX = -DEFAULT_WAIT_SPEED;
                } else {
                    speedX = DEFAULT_WAIT_SPEED;
                }

                // 方向転換判定
                if ( currentChangeSpeedTime - previousChangeSpeedTime > SPEED_CHANGE_INTERVAl ) {
                    speedX += rand.nextInt(RAND_RANGE) - RAND_RANGE / 2; // ランダムに加速を付ける
                    speedX *= -1;  // 方向の反転
                    previousChangeSpeedTime = currentChangeSpeedTime;
                }
            }
        }

        character.setSpeed(speedX, speedY);
    }

    @Override
    protected boolean checkUpdateValid() {
        //== キャラクターが有効範囲内に存在するか
        // かつ、寿命が設定されているかで判定
        return !character.getLifeTime().isPresent();
    }

    @Override
    protected void notUpdate() {
        character.setSpeed(0, 0); // 停止
    }

    @Override
    protected void updateLifeTime() {
        // HPがなくなったとき
        if ( character.getHp() < 1 ) {
            character.disable();
        } else if ( !GameManager.isValid(character) ) {  // 画面外に出たとき
            character.kill();
        }

    }

    @Override
    public PlayableCharacter getCharacter() {
        return character;
    }
}
