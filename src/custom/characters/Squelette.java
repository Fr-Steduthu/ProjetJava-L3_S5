package custom.characters;

import core.character.Monster;

public class Squelette extends Monster {
    
    public Squelette() {
        super("Squelette", 8.0f, 2.0f, 0, 1);
        this.isLootable = true;
        // TODO : this.inventory.add()
    }
}
