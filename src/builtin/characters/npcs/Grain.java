package builtin.characters.npcs;

import builtin.items.SaND;
import core.character.NPC;
import core.Quest;
import hmi.HMI;

public class Grain extends NPC {

	private static final long serialVersionUID = -4833448121672637644L;

	public Grain() {
        super("Grain", "Do yoU wAnT sOmE SaND?", 1);
    }
	
	@Override
	public void interact(Quest context) {
		/*if(this.ask(this.dialogue)) {
			context.getPlayer().getLocation().addItem(new SaND());
			HMI.message("A door has opened somewhere.");
		}else {
			this.speak("Aww yOu MEaNie!");
		}*/
		HMI.error("Unreachable code reached");
	}
}
