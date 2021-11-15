package custom.items;

import core.items.Item;
import core.character.Character;

public class MediumPot extends Item {

	private static final long serialVersionUID = 3203714881926792078L;

	public MediumPot() {
		super("Medium pot");
	}

	@Override
	public String look() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onUse(Object target) {
		if(target instanceof Character) {
			((Character) target).heal(5.0f);
		}
	}

}
