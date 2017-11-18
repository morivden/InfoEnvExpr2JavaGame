package application.component.system.character.controller;

import application.component.objects.character.PlayableCharacter;
import application.component.objects.character.implement_character.TMPCharacter;
import application.component.system.GameManager;
// import com.sun.javafx.geom.Point2D;

import java.awt.Point;
import java.util.Optional;

public class Enemy extends CharacterController {
    private TMPCharacter character;

    public Enemy(TMPCharacter character) {
        this.character = character;
    }

    @Override
    public void update() {
        Optional<PlayableCharacter> playerCharacter = GameManager.getPlayerCharacter();

        double speedX = 0;

        if ( playerCharacter.isPresent() ) {
            Point playerPos = playerCharacter.get().getPosition();
            if ( playerPos.distance(character.getPosition()) < character.RANGE ) {
                double distanceX = playerPos.x - character.getPosition().x;
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
