package application.component.system.character.controller;

import application.component.objects.character.PlayableCharacter;
import application.component.system.GameManager;
import application.component.system.InputManager;

public class Player extends CharacterController {
    private PlayableCharacter character;
    public Player(PlayableCharacter character) {
        this.character = character;
    }

    @Override
    protected void updateSpeed() {
        character.setSpeed(0, 0);
        if ( GameManager.getKeyState(InputManager.KindOfPushedKey.UP_KEY) ) {
            character.setSpeed(character.getXSpeed(), -character.DEFAULT_SPEED);
        } else if ( GameManager.getKeyState(InputManager.KindOfPushedKey.DOWN_KEY) ) {
            character.setSpeed(character.getXSpeed(), character.DEFAULT_SPEED);
        }

        if ( GameManager.getKeyState(InputManager.KindOfPushedKey.LEFT_KEY) ) {
            character.setSpeed(-character.DEFAULT_SPEED, character.getYSpeed());
        } else if ( GameManager.getKeyState(InputManager.KindOfPushedKey.RIGHT_KEY) ) {
            character.setSpeed(character.DEFAULT_SPEED, character.getYSpeed());
        }
    }

    @Override
    protected boolean checkUpdateValid() {
        return true;
    }

    @Override
    protected void notUpdate() {

    }

    @Override
    protected void updateLifeTime() {
        if ( character.getHp() < 1 ) {
            character.disable();
        }
    }

    @Override
    public PlayableCharacter getCharacter() {
        return character;
    }
}
