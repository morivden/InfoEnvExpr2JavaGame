package application.component.system.character.controller;

import application.component.objects.character.PlayableCharacter;
import application.component.system.GameEnvironment;
import application.component.system.GameManager;
import application.component.system.InputManager;
import jdk.internal.org.objectweb.asm.commons.GeneratorAdapter;

public class Player extends CharacterController {
    private PlayableCharacter character;
    public Player(PlayableCharacter character) {
        this.character = character;
    }

    @Override
    protected void updateSpeed() {
        character.setSpeed(0, character.getYSpeed() + (int)GameEnvironment.getGravity());

        if ( GameManager.getKeyState(InputManager.KindOfPushedKey.UP_KEY) ) {
            character.jump();
        } else if ( GameManager.getKeyState(InputManager.KindOfPushedKey.DOWN_KEY) ) {
            // character.setSpeed(character.getXSpeed(), TMPCharacter.DEFAULT_SPEED);
        }

        if ( GameManager.getKeyState(InputManager.KindOfPushedKey.LEFT_KEY) ) {
            character.setSpeed(-character.DEFAULT_SPEED, character.getYSpeed());
        } else if ( GameManager.getKeyState(InputManager.KindOfPushedKey.RIGHT_KEY) ) {
            character.setSpeed(character.DEFAULT_SPEED, character.getYSpeed());
        }
        
        character.updateImage();
    }

    @Override
    protected boolean checkUpdateValid() {
        //== 寿命が設定されているかで判定
        return !character.getLifeTime().isPresent();
    }

    @Override
    protected void notUpdate() {
        // 重力をうけつつ、落下
        character.setSpeed(0, character.getYSpeed() + (int)GameEnvironment.getGravity());
    }

    @Override
    protected void updateLifeTime() {
        if ( character.getHp() < 1 || !GameManager.isValid(character) ) {
            character.disable();
        }
    }

    @Override
    public PlayableCharacter getCharacter() {
        return character;
    }
}
