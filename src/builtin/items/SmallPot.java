package builtin.items;

import core.items.Item;
import core.character.Character;

public class SmallPot extends Item {

	private static final long serialVersionUID = -4228366561990914273L;

	public SmallPot() {
		super("Small pot");
                isUsable = true;
                currentlyUsable = true;
	}

	@Override
	public String look() {
		return "A small effect potion. When used, it regenerates your hp by 3 points.";
	}

	@Override
	protected void onUse(Object target) {
		if(target instanceof Character) {
			((Character) target).heal(3.0);
		}
	}

}
