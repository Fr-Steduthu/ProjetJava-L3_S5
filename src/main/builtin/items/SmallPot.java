package main.builtin.items;

import main.core.items.Item;
import main.core.character.Character;

public class SmallPot extends Item {

	private static final long serialVersionUID = -4228366561990914273L;

	public SmallPot() {
		super("Small pot");
                isUsable = true;
                isCurrentlyUsable = true;
	}

	@Override
	public String look() {
		return "A small effect potion. When used, it regenerates your hp by 3 points.";
	}

	@Override
	protected boolean onUse(Object target) {
		if(target != null) {
			return false;
		}
		
		if(target instanceof Character) {
			((Character) target).heal(3.0);
			return true;
		}else {
			return false;
		}
		
	}

}
