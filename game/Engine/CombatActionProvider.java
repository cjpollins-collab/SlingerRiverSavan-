package Engine;

import characters.Enemy;
import characters.GameCharacter;

public interface CombatActionProvider {
    int chooseAction(GameCharacter actor, Enemy enemy);

    GameCharacter chooseAlly(Party party);
}