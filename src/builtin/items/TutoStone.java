package builtin.items;

import core.items.Item;
import core.places.Place;
import builtin.characters.npcs.TutoMan;
import core.Inventory;
import core.character.Character;

public class TutoStone extends Item {
	private static final long serialVersionUID = 1085466083168420693L;

	public TutoStone() {
		super("Tutorial stone");
	}

	@Override
	public String look() {
		return null;
	}

	@Override
	protected void onUse(Object target) {
		if(target instanceof Place) {
			((Place) target).addNpc(new TutoMan());
		}else if(target instanceof Character) {
			((Character) target).getLocation().addNpc(new TutoMan());
		}
		((Inventory)this.getLocation()).getOwner().hurt(5.0);
	}

}
