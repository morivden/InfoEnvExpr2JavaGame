package application.component.system.character.controller;

import application.component.objects.character.PlayableCharacter;
import application.component.objects.character.implement_character.TMPCharacter;
import application.component.system.GameManager;
import javafx.geometry.Point2D;

import java.util.Optional;

public class Enemy extends CharacterController {
    private TMPCharacter character;

    public Enemy(TMPCharacter character) {
        this.character = character;
    }


    @Override
    public void update() {
        int range = 200;  // 索敵範囲
        Optional<PlayableCharacter> playerCharacter = GameManager.getPlayerCharacter();

        double speedX = 0;

        if ( playerCharacter.isPresent() ) {
            Point2D playerPos = playerCharacter.get().getPosition();
            if ( playerPos.distance(character.getPosition()) < range ) {
                double distanceX = playerPos.getX() - character.getPosition().getX();
                speedX = ((TMPCharacter)character).DEFAULT_SPEED * Math.signum(distanceX);

                if ( Math.abs(speedX) > Math.abs(distanceX) ) {
                    speedX = distanceX;
                }

                speedX *= 0.5;
            }
        }

        ((TMPCharacter)character).setSpeed((int)speedX, 0);
    }

    public TMPCharacter getCharacter() {
        return character;
    }
}
