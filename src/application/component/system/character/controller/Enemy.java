package application.component.system.character.controller;

import application.component.objects.character.PlayableCharacter;

public class Enemy implements CharacterController {
    private PlayableCharacter character;

    public Enemy(PlayableCharacter character) {
        this.character = character;
    }

    @Override
    public void update() {

    }
}
