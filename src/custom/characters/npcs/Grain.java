package custom.characters.npcs;

import core.character.NPC;
import custom.items.SaND;

public class Grain extends NPC {

	private static final long serialVersionUID = -4833448121672637644L;

	public Grain() {
        super("Grain", "Do yoU wAnT sOmE SaND?", 1);
        this.inventory.addItem(new SaND());
    }
}
