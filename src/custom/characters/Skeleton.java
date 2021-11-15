package custom.characters;

import core.character.Monster;

public class Skeleton extends Monster {
    
    public Skeleton() {
        super("Skeleton", 8.0f, 2.0f, 0, 1);
        this.isLootable = true;
        // TODO : this.inventory.add()
    }
}
