package main.builtin.items;

import main.core.items.Item;
import main.core.character.Character;

public class Flesh extends Item {

	private static final long serialVersionUID = 2949132669646475417L;

	public Flesh() {
		super("Flesh");
                isUsable = true;
                isCurrentlyUsable = true;
	}

	@Override
	public String look() {
		return "Some flesh. Regenerates your AR by 2 points when used";
	}

	@Override
	protected boolean onUse(Object target) {
		if(target instanceof Character) {
			((Character) target).giveAR(2.0);
			return true;
		}else {
			return false;
		}
	}

}
