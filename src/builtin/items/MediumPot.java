package builtin.items;

import core.items.Item;
import core.character.Character;

public class MediumPot extends Item {

	private static final long serialVersionUID = 3203714881926792078L;

	public MediumPot() {
		super("Medium pot");
                isUsable = true;
                currentlyUsable = true;
	}

	@Override
	public String look() {
		return "A medium effect potion. When used, it regenerates your hp by 5 points.";
	}

	@Override
	protected boolean onUse(Object target) {
		if(target instanceof Character) {
			((Character) target).heal(5.0);
			return true;
		}
		return false;
	}

}
