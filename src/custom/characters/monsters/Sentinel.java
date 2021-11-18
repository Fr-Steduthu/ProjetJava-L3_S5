package custom.characters.monsters;

import core.character.Monster;
import hmi.HMI;

public class Sentinel extends Monster {

	private static final long serialVersionUID = 979388609507722307L;

	public Sentinel() {
        super("Sentinel", 1.0, 2.0, 1, 0);
        this.isLootable = true;
        // TODO : this.inventory.add()
    }
    
    @Override
    public void hurt(double damageTaken) {
        HMI.message("It had no effect.");
    }
}
