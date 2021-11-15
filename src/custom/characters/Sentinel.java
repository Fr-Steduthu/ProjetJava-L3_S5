package custom.characters;

import core.character.Monster;
import hmi.HMI;

public class Sentinel extends Monster {
    
    public Sentinel() {
        super("Sentinel", 1.0f, 2.0f, 1, 0);
        this.isLootable = true;
        // TODO : this.inventory.add()
    }
    
    @Override
    public void hurt(int dammageTaken) {
        HMI.message("It had no effect.");
    }
}
