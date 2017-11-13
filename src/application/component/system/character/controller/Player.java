package application.component.system.character.controller;

import application.component.objects.character.PlayableCharacter;

public class Player extends CharacterController {
    private PlayableCharacter character;

    public Player(PlayableCharacter character) {
        this.character = character;
    }

    @Override
    public void update() {

    }
}
