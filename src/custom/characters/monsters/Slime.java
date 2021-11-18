package custom.characters.monsters;

import core.character.Monster;

public class Slime extends Monster {

	private static final long serialVersionUID = 4468030219187598626L;

	public Slime() {
        super("Slime", 5.0, 1.5, 1);
        this.isLootable = true;
        // TODO : this.inventory.add()
    }
    
}
