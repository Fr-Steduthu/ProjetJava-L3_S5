package builtin.characters.npcs;

import builtin.items.TutoStone;
import core.character.NPC;

public class TutoMan extends NPC {

	private static final long serialVersionUID = 1443591607874829245L;

	public TutoMan() {
        super("Tuto-MAN", "TODO : THIS IS A PLACEHOLDER FOR NOW", 1);
        this.inventory.addItem(new TutoStone());
    }
    
}