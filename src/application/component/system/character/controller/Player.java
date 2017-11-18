package application.component.system.character.controller;

import application.component.objects.character.PlayableCharacter;
import application.component.objects.character.implement_character.TMPCharacter;
import application.component.system.GameManager;
import application.component.system.InputManager;

public class Player extends CharacterController {
    // TODO PlayableCharacterが実装、要修正
    // TMPCharacterをPlayableCharacterに

    private PlayableCharacter character;
//    private TMPCharacter character;
    public Player(PlayableCharacter character) {
        this.character = character;
    }

//    public Player(TMPCharacter character) {
//        this.character = character;
//    }

    @Override
    public void update() {
        character.setSpeed(0, 0);
        if ( GameManager.getKeyState(InputManager.KindOfPushedKey.UP_KEY) ) {
            character.setSpeed(character.getXSpeed(), -TMPCharacter.DEFAULT_SPEED);
        } else if ( GameManager.getKeyState(InputManager.KindOfPushedKey.DOWN_KEY) ) {
            character.setSpeed(character.getXSpeed(), TMPCharacter.DEFAULT_SPEED);
        }

        if ( GameManager.getKeyState(InputManager.KindOfPushedKey.LEFT_KEY) ) {
            character.setSpeed(-TMPCharacter.DEFAULT_SPEED, character.getYSpeed());

        } else if ( GameManager.getKeyState(InputManager.KindOfPushedKey.RIGHT_KEY) ) {
            character.setSpeed(TMPCharacter.DEFAULT_SPEED, character.getYSpeed());
        }
    }

    // TODO 要修正
//    public PlayableCharacter getCharacter() {
//        return character;
//    }


    public PlayableCharacter getCharacter() {
        return character;
    }
}
