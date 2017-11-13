package application.component.system.character.controller;

import application.component.objects.character.PlayableCharacter;

public class Player implements CharacterController {
    private PlayableCharacter character;

    public Player(PlayableCharacter character) {
        this.character = character;
    }

    @Override
    public void update() {

    }
}
