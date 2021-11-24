package builtin.characters.npcs;

import builtin.items.SaND;
import core.character.NPC;

public class Grain extends NPC {

	private static final long serialVersionUID = -4833448121672637644L;

	public Grain() {
        super("Grain", "Do yoU wAnT sOmE SaND?", 1);
        this.inventory.addItem(new SaND());
    }
}
