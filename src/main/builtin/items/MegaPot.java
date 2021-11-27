package builtin.items;

import core.items.Item;
import core.character.Character;

public class MegaPot extends Item {

	private static final long serialVersionUID = -1220819768902987370L;

	public MegaPot() {
		super("Mega-pot");
                isUsable = true;
                isCurrentlyUsable = true;
	}

	@Override
	public String look() {
		return "A mega effect potion. When used, it regenerates your hp by 10 points.";
	}

	@Override
	protected boolean onUse(Object target) {
		if(target != null) {
			return false;
		}
		
		if(target instanceof Character) {
			((Character) target).heal(10.0);
			return true;
		}
		
		return false;
	}

}
