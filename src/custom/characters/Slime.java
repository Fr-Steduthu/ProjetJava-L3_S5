package custom.characters;

import core.character.Monster;

public class Slime extends Monster {
    
    public Slime() {
        super("Slime", 5.0f, 1.5f, 1);
        this.isLootable = true;
        // TODO : this.inventory.add()
    }
    
}
