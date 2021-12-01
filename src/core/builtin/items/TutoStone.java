package core.builtin.items;

import core.builtin.characters.npcs.TutoMan;
import core.game.Inventory;
import core.hmi.HMI;
import core.item.Usable;

public class TutoStone extends Usable {
	private static final long serialVersionUID = 1085466083168420693L;

	public TutoStone() {
		super("Tutorial stone");
	}

	@Override
	public String look() {
		return "A special stone. When used, it will summon the Tuto-MAN in your current room"/* and inflict 5 points of damage to everyone."*/;
	}

	@Override

	protected boolean onUse(Object target) {
		if(target != null) {
			return false;
		}
		((Inventory) this.getLocation()).getOwner().getLocation().addNpc(new TutoMan());
		HMI.debug("Tuto-stone used");
		//((Inventory) this.getLocation()).getOwner().getLocation().
		return true;
	}

}
