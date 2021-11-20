package custom.characters.monsters;

import core.character.Monster;
import custom.items.SaNDCure;
import hmi.HMI;

public class Sentinel extends Monster {

	private static final long serialVersionUID = 979388609507722307L;

	public Sentinel() {
        super("Sentinel", 1.0, 2.0, 1, 0);
        this.isLootable = true;
        this.inventory.addItem(new SaNDCure());
    }
    
    @Override
    public void hurt(double damageTaken) {
        HMI.message("It had no effect.");
    }
}
