package custom.items;

import core.items.Item;
import core.character.Character;

public class SmallPot extends Item {

	private static final long serialVersionUID = -4228366561990914273L;

	public SmallPot() {
		super("Small pot");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String look() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onUse(Object target) {
		if(target instanceof Character) {
			((Character) target).heal(3.0f);
		}
	}

}
