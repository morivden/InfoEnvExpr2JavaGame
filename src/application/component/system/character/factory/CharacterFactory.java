package application.component.system.character.factory;

import application.component.system.character.controller.CharacterController;

public interface CharacterFactory<T extends CharacterController> {
    void updateAll();
    T create();
}
