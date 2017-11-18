package application.component.system.character.controller;

import application.component.objects.character.PlayableCharacter;
import application.component.objects.character.implement_character.TMPCharacter;
import application.component.system.GameEnvironment;
import application.component.system.GameManager;
import application.component.system.InputManager;

public class Player extends CharacterController {
    private PlayableCharacter character;
    public Player(PlayableCharacter character) {
        this.character = character;
    }

    @Override
    public void update() {
        character.setSpeed(0, character.getYSpeed() + (int)GameEnvironment.getGravity());
        if ( GameManager.getKeyState(InputManager.KindOfPushedKey.UP_KEY) ) {
            if (character.onGround) {
                // character.setSpeed(character.getXSpeed(), -TMPCharacter.DEFAULT_SPEED);
                character.setSpeed(character.getXSpeed(), -14);
                character.onGround = false;
            }
            // character.setSpeed(character.getXSpeed(), -TMPCharacter.DEFAULT_SPEED);
        } else if ( GameManager.getKeyState(InputManager.KindOfPushedKey.DOWN_KEY) ) {
            // character.setSpeed(character.getXSpeed(), TMPCharacter.DEFAULT_SPEED);
        }

        if ( GameManager.getKeyState(InputManager.KindOfPushedKey.LEFT_KEY) ) {
            character.setSpeed(-TMPCharacter.DEFAULT_SPEED, character.getYSpeed());
        } else if ( GameManager.getKeyState(InputManager.KindOfPushedKey.RIGHT_KEY) ) {
            character.setSpeed(TMPCharacter.DEFAULT_SPEED, character.getYSpeed());
        }
    }

    public PlayableCharacter getCharacter() {
        return character;
    }
}
