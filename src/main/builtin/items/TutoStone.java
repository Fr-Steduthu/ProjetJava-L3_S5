package main.builtin.items;

import main.core.items.Item;
import main.builtin.characters.npcs.TutoMan;
import main.core.Inventory;

public class TutoStone extends Item {
	private static final long serialVersionUID = 1085466083168420693L;

	public TutoStone() {
		super("Tutorial stone");
	}

	@Override
	public String look() {
		return "A special stone. When used, it will summon the Tuto-MAN in your current room and inflict 5 points of damage to everyone.";
	}

	@Override

	protected boolean onUse(Object target) {
		if(target != null) {
			return false;
		}
		((Inventory) this.getLocation()).getOwner().getLocation().addNpc(new TutoMan());
		return true;
	}

}
