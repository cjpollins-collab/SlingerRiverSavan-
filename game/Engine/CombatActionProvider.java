package Engine;

import Items.Inventory;
import characters.Enemy;
import characters.GameCharacter;
import java.util.List;

public interface CombatActionProvider {

    int chooseAction(GameCharacter actor, Enemy enemy);

    GameCharacter chooseAlly(Party party);

    Enemy chooseEnemy(List<Enemy> enemies);

    int chooseInventoryItem(Inventory inventory);

    GameCharacter chooseItemTarget(Party party);

    int chooseStatusOption();
}