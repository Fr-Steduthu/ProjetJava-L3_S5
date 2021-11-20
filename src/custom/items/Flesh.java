package custom.items;

import core.items.Item;
import core.character.Character;

public class Flesh extends Item {

	private static final long serialVersionUID = 2949132669646475417L;

	public Flesh() {
		super("Flesh");
                isUsable = true;
                currentlyUsable = true;
	}

	@Override
	public String look() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onUse(Object target) {
		if(target instanceof Character) {
			((Character) target).giveAR(2.0);;
		}
	}

}
